package ywphsm.ourneighbor.controller.membercontoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import ywphsm.ourneighbor.domain.dto.ReviewMemberDTO;
import ywphsm.ourneighbor.domain.dto.store.StoreDTO;
import ywphsm.ourneighbor.domain.dto.Member.MemberDTO;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.member.MemberOfStore;
import ywphsm.ourneighbor.service.MemberService;
import ywphsm.ourneighbor.service.ReviewService;
import ywphsm.ourneighbor.service.login.SessionConst;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MyPageController {

    private final MemberService memberService;
    private final ReviewService reviewService;

    @GetMapping("/user/my-page")
    public String myPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                         Model model) {

        Member findMember = memberService.findById(member.getId());
        MemberDTO.Detail detail = new MemberDTO.Detail(findMember);
        model.addAttribute("memberDetail", detail);
        return "member/my_page";
    }

    @GetMapping("/user/my-like")
    public String myLike(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                         Model model) {
        Member findById = memberService.findById(member.getId());

        List<StoreDTO.Detail> likeList = findById.getMemberOfStoreList().stream()
                .filter(MemberOfStore::isStoreLike)
                .map(memberOfStore -> new StoreDTO.Detail(memberOfStore.getStore()))
                .collect(Collectors.toList());

        int count = 0;
        if (!likeList.isEmpty()) {
            count = likeList.size();
        }

        model.addAttribute("like", likeList);
        model.addAttribute("count", count);
        return "member/my_like";
    }

    @GetMapping("/seller/seller-page")
    public String sellerPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                         Model model) {
        Member byId = memberService.findById(member.getId());
        List<StoreDTO.Detail> collect = byId.getMemberOfStoreList().stream()
                .filter(MemberOfStore::isMyStore)
                .map(memberOfStore -> new StoreDTO.Detail(memberOfStore.getStore()))
                .collect(Collectors.toList());

        model.addAttribute("storeDto", collect);

        return "member/seller_page";
    }
    @GetMapping("/user/member-edit/review")
    public String MyReview(@SessionAttribute(value = SessionConst.LOGIN_MEMBER) Member member,
                           Model model) {

        List<ReviewMemberDTO> content = reviewService.myReviewList(member.getId());

        int count = 0;
        if (!content.isEmpty()) {
            count = content.size();
        }

        model.addAttribute("review", content);
        model.addAttribute("count", count);
        return "member/my_review";
    }

    @GetMapping("/admin/admin-page")
    public String adminPage() {
        return "member/admin_page";
    }
}
