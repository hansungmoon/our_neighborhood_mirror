package ywphsm.ourneighbor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ywphsm.ourneighbor.controller.form.ReviewForm;
import ywphsm.ourneighbor.domain.Review;
import ywphsm.ourneighbor.domain.dto.ReviewDTO;
import ywphsm.ourneighbor.domain.dto.ReviewMemberDTO;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.service.ReviewService;
import ywphsm.ourneighbor.service.StoreService;
import ywphsm.ourneighbor.service.login.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    private final StoreService storeService;


    @GetMapping("/store/{storeId}/createReview")
    public String createReview(@PathVariable Long storeId,
                               @ModelAttribute(name = "reviewDTO") ReviewDTO.Add reviewDTO,
                               @SessionAttribute(value = SessionConst.LOGIN_MEMBER) Member member) {

        reviewDTO.setStoreId(storeId);
        reviewDTO.setMemberId(member.getId());

        return "review/createReview";
    }

    @GetMapping("/member_edit/review")
    public String MyReview(@SessionAttribute(value = SessionConst.LOGIN_MEMBER) Member member,
                           Model model) {

        Slice<ReviewMemberDTO> reviewMemberDTOS = reviewService.pagingMyReview(member.getId(), 0);
        List<ReviewMemberDTO> content = reviewMemberDTOS.getContent();
        log.info("content={}", content);

        model.addAttribute("review", content);
        model.addAttribute("memberId", member.getId());
        return "member/MyReview";
    }
}
