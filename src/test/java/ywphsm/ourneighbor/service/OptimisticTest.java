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
    public void 동시에_100개의요청() throws InterruptedException, IOException, ParseException {
        ReviewDTO.Add dto = ReviewDTO.Add.builder()
                .content("1")
                .rating(1)
                .storeId(40016L)
                .memberId(37001L)
                .build();

        int numberOfThreads = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Future<?> future = executorService.submit(
                () -> reviewService.save(dto, null));
        Future<?> future2 = executorService.submit(
                () -> reviewService.save(dto, null));
        Future<?> future3 = executorService.submit(
                () -> reviewService.save(dto, null));

        Exception result = new Exception();

        try {
            future.get();
            future2.get();
            future3.get();
        } catch (ExecutionException e) {
            result = (Exception) e.getCause();
        }

        assertTrue(result instanceof OptimisticLockingFailureException);
    }
}
