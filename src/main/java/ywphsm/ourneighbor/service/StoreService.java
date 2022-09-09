package ywphsm.ourneighbor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ywphsm.ourneighbor.domain.Category;
import ywphsm.ourneighbor.domain.CategoryOfStore;
import ywphsm.ourneighbor.domain.dto.StoreDTO;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.repository.store.StoreRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true) // 데이터 변경 X
public class StoreService {

    private final StoreRepository storeRepository;

    // 매장 등록
    @Transactional
    public Long saveStore(StoreDTO.Add dto, Category category) {

        Store store = dto.toEntity();

        CategoryOfStore categoryOfStore = new CategoryOfStore(category, store);
        log.info("store={}", store);
        log.info("categoryOfStore={}", categoryOfStore);

        CategoryOfStore result = categoryOfStore.addCategory(category, store);

        log.info("result={}", result.getStore());
        log.info("result={}", result.getCategory());


        storeRepository.save(store);
        return store.getId();
    }

    // 전체 매장 조회
    public List<Store> findStores() {
        return storeRepository.findAll();
    }

    // 매장 하나 조회
    public Store findOne(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(NoSuchElementException::new);

        store.autoUpdateStatus(store.getOffDays(), store.getOpeningTime(), store.getClosingTime(), store.getBreakStart(), store.getBreakEnd());

        return store;
    }

    // 매장 이름으로 조회
    public List<Store> findByName(String name) {
        return storeRepository.findByName(name);
    }


    // 검색어 포함 매장명 조회
    public List<Store> searchByKeyword(String keyword) {
        List<Store> stores = storeRepository.searchByKeyword(keyword);

        for (Store store : stores) {
            store.autoUpdateStatus(store.getOffDays(), store.getOpeningTime(), store.getClosingTime(), store.getBreakStart(), store.getBreakEnd());
        }

        return stores;
    }

}
