package ywphsm.ourneighbor.unit.service;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ywphsm.ourneighbor.domain.dto.ReviewDTO;
import ywphsm.ourneighbor.domain.dto.hashtag.HashtagDTO;
import ywphsm.ourneighbor.domain.hashtag.Hashtag;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.store.Review;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.repository.member.MemberRepository;
import ywphsm.ourneighbor.repository.review.ReviewRepository;
import ywphsm.ourneighbor.repository.store.StoreRepository;
import ywphsm.ourneighbor.service.ReviewService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    ReviewService reviewService;

    @Test
    @DisplayName("리뷰 저장")
    void should_SaveReview() throws IOException, ParseException {
        // given
        Store store = Store.builder()
                .name("매장")
                .build();

        store = storeRepository.save(store);
        Long mockStoreId = store.getId();
//        setField(store, "id", mockStoreId);

        Member member = Member.builder()
                .username("회원")
                .build();

        member = memberRepository.save(member);
        Long mockMemberId = member.getId();
//        setField(member, "id", mockMemberId);

        ReviewDTO.Add dto = ReviewDTO.Add.builder()
                .content("리뷰내용")
                .rating(5)
                .memberId(mockMemberId)
                .storeId(mockStoreId)
                .build();

        given(memberRepository.findById(mockMemberId)).willReturn(Optional.of(member));
        given(storeRepository.findById(mockStoreId)).willReturn(Optional.of(store));
        given(reviewRepository.save(any())).willReturn(dto.toEntity());

        // when
        Review review = reviewService.save(dto, "");

        // then
        assertThat(dto.getContent()).isEqualTo(review.getContent());
        assertThat(dto.getRating()).isEqualTo(review.getRating());
        then(reviewRepository).should().save(any());
    }
}
