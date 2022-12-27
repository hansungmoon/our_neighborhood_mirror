package ywphsm.ourneighbor.service.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ywphsm.ourneighbor.domain.dto.ReviewDTO;
import ywphsm.ourneighbor.domain.store.Review;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.service.ReviewService;

@Service
@RequiredArgsConstructor
public class OptimisticLockStoreFacade {

    private ReviewService reviewService;

    /**
     *  업데이트 시도시 락이 걸려있으면 일정간격을 두고 계속 재시도
     */
    public void reviewSave(ReviewDTO.Add dto, String hashtag) throws InterruptedException {
        while (true) {
            try {
                reviewService.save(dto, hashtag);

            } catch (Exception e) {
                System.out.println("e.getMessage() = " + e.getMessage());
                Thread.sleep(50);
            }
        }
    }
}
