package ywphsm.ourneighbor.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ywphsm.ourneighbor.service.MemberService;

@Controller
@RequiredArgsConstructor
public class EmailController {

    private final MemberService memberService;

    @GetMapping("/confirm-email")
    public String viewConfirmEmail(@RequestParam String tokenId) {
        memberService.confirmEmail(tokenId);

        return "redirect:/sign_in";
    }
}
