package ywphsm.ourneighbor.service;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import ywphsm.ourneighbor.domain.dto.ReviewDTO;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.repository.store.StoreRepository;

import java.io.IOException;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OptimisticTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private StoreRepository storeRepository;

    @Test
    public void 동시에_100개의요청() throws InterruptedException {
        ReviewDTO.Add dto = ReviewDTO.Add.builder()
                .content("1")
                .rating(1)
                .storeId(40016L)
                .memberId(37001L)
                .build();

        int threadCount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    reviewService.updateStoreRating(dto.getStoreId(), dto.toEntity(), true);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Store store = storeRepository.findById(40016L).orElseThrow();

        // 100 - (100 * 1) = 0
        assertEquals(1, store.getRatingTotal());
    }
}
