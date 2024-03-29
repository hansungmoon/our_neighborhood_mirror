package ywphsm.ourneighbor.repository.requestaddstore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ywphsm.ourneighbor.domain.dto.RequestAddStoreDTO;

public interface RequestAddStoreRepositoryCustom {

    Page<RequestAddStoreDTO.Detail> requestAddStorePage(Pageable pageable);
}
