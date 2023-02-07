package ywphsm.ourneighbor.unit.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ywphsm.ourneighbor.domain.embedded.Address;
import ywphsm.ourneighbor.domain.store.RequestAddStore;
import ywphsm.ourneighbor.domain.store.Review;
import ywphsm.ourneighbor.repository.requestaddstore.RequestAddStoreRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RequestAddStoreRepositoryTest {

    @Autowired
    TestEntityManager tem;

    @Autowired
    RequestAddStoreRepository requestAddStoreRepository;

    @Test
    @DisplayName("가게추가 요청 저장")
    void should_SaveARequestAddStore() {
        RequestAddStore requestAddStore = RequestAddStore.builder()
                .name("가게1")
                .address(new Address("테스트 로", "11111", "48017", "상세 주소"))
                .content("가게요청1")
                .build();


        RequestAddStore savedRequestAddStore = requestAddStoreRepository.save(requestAddStore);

        assertThat(savedRequestAddStore.getContent()).isEqualTo(requestAddStore.getContent());
    }

}
