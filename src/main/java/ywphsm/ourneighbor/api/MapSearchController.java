package ywphsm.ourneighbor.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.repository.store.dto.SimpleSearchStoreDTO;
import ywphsm.ourneighbor.service.CategoryService;
import ywphsm.ourneighbor.service.StoreService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MapSearchController {

    private final StoreService storeService;
    private final CategoryService categoryService;

    // 메뉴 리스트는 불러오지 않음
    // 단순 조회이므로 fetch 조인으로 최적화
    @GetMapping(value = "/searchStores", produces = "application/json;charset=utf-8")
    public ResultClass<?> searchStores(@RequestParam String keyword) {
        List<Store> findStores = storeService.searchByKeyword(keyword);
        List<SimpleSearchStoreDTO> collect = findStores.stream()
                .map(SimpleSearchStoreDTO::new)
                .collect(Collectors.toList());
        return new ResultClass<>(collect.size(), collect);
    }
}
