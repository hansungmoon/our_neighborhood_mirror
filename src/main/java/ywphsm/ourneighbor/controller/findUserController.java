package ywphsm.ourneighbor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ywphsm.ourneighbor.controller.form.FindPasswordForm;
<<<<<<< HEAD
import ywphsm.ourneighbor.service.MemberService;
=======
import ywphsm.ourneighbor.controller.form.FindUserIdForm;
import ywphsm.ourneighbor.service.MemberService;

>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class findUserController {

    private final MemberService memberService;

<<<<<<< HEAD
    @GetMapping("/findId")
    public String findId() {
        return "";
=======
    @GetMapping("/findUserId")
    public String findId(@ModelAttribute FindUserIdForm findUserIdForm) {
        return "login/findUserId";
    }

    @PostMapping("/findUserId")
    public String findId(@Valid @ModelAttribute FindUserIdForm findUserIdForm,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login/findUserId";
        }

        memberService.findUserId(findUserIdForm.getEmail());

        return "redirect:/login";
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
    }

    @GetMapping("/findPassword")
    public String findPassword(@ModelAttribute FindPasswordForm findPasswordForm) {
        return "login/findPassword";
    }

    @PostMapping("/findPassword")
    public String passwordEdit(@Valid @ModelAttribute FindPasswordForm findPasswordForm,
                               BindingResult bindingResult) {

        if (!findPasswordForm.getNewPassword().equals(findPasswordForm.getPasswordCheck())) {
            bindingResult.reject("passwordCheck");
        }

<<<<<<< HEAD
=======
        if (memberService.userIdCheck(findPasswordForm.getUserId()) == null) {
            bindingResult.reject("userIdCheck");
        }

>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
        if (bindingResult.hasErrors()) {
            return "login/findPassword";
        }

        String encodedPassword = memberService.encodedPassword(findPasswordForm.getNewPassword());

        memberService.updatePassword(findPasswordForm.getUserId(), encodedPassword);

<<<<<<< HEAD
        return "redirect:/logout";
=======
        return "redirect:/login";
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
    }
}
