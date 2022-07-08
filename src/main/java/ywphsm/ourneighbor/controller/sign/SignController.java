package ywphsm.ourneighbor.controller.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ywphsm.ourneighbor.controller.form.MemberForm;
import ywphsm.ourneighbor.entity.Gender;
import ywphsm.ourneighbor.entity.Member;
import ywphsm.ourneighbor.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final MemberService memberService;

    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @GetMapping("/sign_up")
    public String SignUp(@ModelAttribute MemberForm memberForm) {
        return "sign/signUpForm";
    }

    @PostMapping("/sign_up")
    public String saveMember(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult) {

        if (memberForm.getUsername() != null) {
            if (memberService.doubleCheck(memberForm.getUsername())) {
                bindingResult.reject("doubleCheck", new Object[]{memberForm.getUsername()}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            return "sign/signUpForm";
        }

        Member member = new Member(memberForm.getUsername(), memberForm.getAge(), memberForm.getPhoneNumber(),
                memberForm.getGender(), memberForm.getLoginId(), memberForm.getPassword(),
                memberForm.getEmail(), memberForm.getName()
        );

        memberService.join(member);
        return "redirect:/";
    }



}
