package ywphsm.ourneighbor.controller.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ywphsm.ourneighbor.controller.form.EditForm;
import ywphsm.ourneighbor.controller.form.PasswordEditForm;
import ywphsm.ourneighbor.entity.Member;
import ywphsm.ourneighbor.service.MemberService;
import ywphsm.ourneighbor.service.login.SessionConst;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class EditController {

    private final MemberService memberService;

    @GetMapping("/member_edit")
    public String memberEdit(@ModelAttribute EditForm editForm,
                             @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                             Model model) {

        editForm.setId(member.getId());
        editForm.setEmail(member.getEmail());
        editForm.setAge(member.getAge());
        editForm.setName(member.getName());
        editForm.setUsername(member.getUsername());
        editForm.setPhoneNumber(member.getPhoneNumber());


        return "sign/editForm";
    }

    @PostMapping("/member_edit")
    public String memberEdit(@Valid @ModelAttribute EditForm editForm, BindingResult bindingResult) {

        if (memberService.doubleCheck(editForm.getUsername())) {
            bindingResult.reject("doubleCheck", new Object[]{editForm.getUsername()}, null);

            if (bindingResult.hasErrors()) {
                return "sign/editForm";
            }
        }

        memberService.update(editForm.getId(), editForm.getUsername(), editForm.getAge(), editForm.getPhoneNumber(),
                editForm.getEmail(), editForm.getName()
                );

        return "redirect:/";
    }

    @GetMapping("/member_edit/password_edit")
    public String passwordEdit(@ModelAttribute PasswordEditForm passwordEditForm) {
        return "sign/passwordEditForm";
    }

    @PostMapping("/member_edit/password_edit")
    public String passwordEdit(@Valid @ModelAttribute PasswordEditForm passwordEditForm,
                               BindingResult bindingResult,
                               @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member) {

        if (!memberService.passwordCheck(member.getPassword(), passwordEditForm.getBeforePassword())) {
            bindingResult.reject("passwordEqCheck");
        }

        if (!passwordEditForm.getAfterPassword().equals(passwordEditForm.getPasswordCheck())) {
            bindingResult.reject("passwordCheck");
        }

        if (bindingResult.hasErrors()) {
            return "/sign/passwordEditForm";
        }

        String encodedPassword = memberService.encodedPassword(passwordEditForm.getAfterPassword());

        memberService.updatePassword(member.getId(), encodedPassword);

        return "redirect:/logout";
    }


}
