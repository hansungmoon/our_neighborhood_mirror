package ywphsm.ourneighbor.unit.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ywphsm.ourneighbor.repository.review.ReviewRepository;
import ywphsm.ourneighbor.service.ReviewService;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewService reviewService;
}
