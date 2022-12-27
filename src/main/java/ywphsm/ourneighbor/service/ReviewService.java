package ywphsm.ourneighbor.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ywphsm.ourneighbor.config.DateUtils;
import ywphsm.ourneighbor.domain.store.Review;
import ywphsm.ourneighbor.domain.dto.ReviewDTO;
import ywphsm.ourneighbor.domain.dto.ReviewMemberDTO;
import ywphsm.ourneighbor.domain.dto.hashtag.HashtagDTO;
import ywphsm.ourneighbor.domain.file.AwsS3FileStore;
import ywphsm.ourneighbor.domain.file.FileStore;
import ywphsm.ourneighbor.domain.file.UploadFile;
import ywphsm.ourneighbor.domain.hashtag.Hashtag;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.repository.hashtag.HashtagRepository;
import ywphsm.ourneighbor.repository.member.MemberRepository;
import ywphsm.ourneighbor.repository.review.ReviewRepository;
import ywphsm.ourneighbor.repository.store.StoreRepository;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;

import static ywphsm.ourneighbor.domain.hashtag.HashtagOfStore.linkHashtagAndStore;
import static ywphsm.ourneighbor.domain.hashtag.HashtagUtil.*;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final HashtagRepository hashtagRepository;
    private final AwsS3FileStore awsS3FileStore;

    private final FileStore fileStore;
    private final EntityManager entityManager;

    @Transactional
    public Long save(ReviewDTO.Add dto, String hashtag) throws IOException, ParseException {
        Member linkedMember = memberRepository.findById(dto.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다. id = " + dto.getMemberId()));

        Review review = dto.toEntity();
        Store linkedStore = updateStoreRating(dto.getStoreId(), review, true);
        review.setStore(linkedStore);
        review.setMember(linkedMember);
        linkedMember.addReview(review);

        if (dto.getFile() != null) {
            List<UploadFile> newUploadFiles = awsS3FileStore.storeFiles(dto.getFile());
            review.addFile(newUploadFiles);
        }

        if (!hashtag.isEmpty()) {
            List<String> hashtagNameList = getHashtagNameList(hashtag);
            saveHashtagLinkedStore(linkedStore, hashtagNameList);
        }

        return reviewRepository.save(review).getId();
    }

    @Transactional
    public Long delete(Long storeId, Long reviewId) {
        Review review = findOne(reviewId);
//        Store store = storeRepository.findByIdWithOptimisticLock(storeId);

        review.getFileList().stream()
                .map(UploadFile::getStoredFileName).forEach(awsS3FileStore::deleteFile);

//        double count = 0;
//        if (store.getReviewList().size() > 0) {
//            count = store.getReviewList().size() - 1;
//        }

        updateStoreRating(storeId, review, false);
        entityManager.flush();
        entityManager.clear();

        reviewRepository.deleteById(reviewId);
        entityManager.flush();
        entityManager.clear();

        return reviewId;
    }

    public Review findOne(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 리뷰입니다. id = " + reviewId));
    }

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    public Slice<ReviewMemberDTO> pagingReview(Long storeId, int page) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        Slice<ReviewMemberDTO> reviewMemberDTOS = reviewRepository.reviewPage(pageRequest, storeId);
        findImg(reviewMemberDTOS.getContent());
        dateDifference(reviewMemberDTOS.getContent());

        return reviewMemberDTOS;
    }

    public List<ReviewMemberDTO> myReviewList(Long memberId) {
        List<ReviewMemberDTO> reviewMemberDTOS = reviewRepository.myReview(memberId);
        findImg(reviewMemberDTOS);
        return dateDifference(reviewMemberDTOS);
    }

    public Store updateStoreRating(Long storeId, Review review, boolean saveOrDelete) {
        Store store = storeRepository.findByIdWithOptimisticLock(storeId);
        double count = store.getReviewList().size();

        if (saveOrDelete) {
            store.increaseRatingTotal(review.getRating());
        } else {
            store.decreaseRatingTotal(review.getRating());
            if (count > 0) {
                count = store.getReviewList().size() - 1;
            }
        }

        if (store.getReviewList().size() == 0) {
            store.updateRatingAverage(0);
            store.addReview(review);
            storeRepository.saveAndFlush(store);
            return store;
        }

        double ratingTotal = store.getRatingTotal();
        double average = ratingTotal / count;
        double reviewAverage = Math.round(average * 10) / 10.0;

        store.updateRatingAverage(reviewAverage);
        store.addReview(review);
        storeRepository.saveAndFlush(store);
        return store;

    }

    private void saveHashtagLinkedStore(Store store, List<String> hashtagNameList) {
        for (String name : hashtagNameList) {
            boolean duplicateCheck = hashtagRepository.existsByName(name);

            Hashtag newHashtag;

            if (!duplicateCheck) {
                HashtagDTO hashtagDTO = HashtagDTO.builder()
                        .name(name)
                        .build();

                newHashtag = hashtagRepository.save(hashtagDTO.toEntity());
            } else {
                newHashtag = hashtagRepository.findByName(name)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 해쉬태그입니다."));
            }

            linkHashtagAndStore(newHashtag, store);
        }
    }

    public void findImg(List<ReviewMemberDTO> content) {
        for (ReviewMemberDTO reviewMemberDTO : content) {
            List<String> imgUrl = reviewRepository.reviewImageUrl(reviewMemberDTO.getReviewId());
            reviewMemberDTO.setUploadImgUrl(imgUrl);
        }
    }

    public List<ReviewMemberDTO> dateDifference(List<ReviewMemberDTO> content) {
        for (ReviewMemberDTO reviewMemberDTO : content) {
            String difference = DateUtils.periodDate(reviewMemberDTO.getCreateDate());
            reviewMemberDTO.setDateDifference(difference);
        }
        return content;
    }
}
