package ywphsm.ourneighbor.controller.sign;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ywphsm.ourneighbor.controller.dto.CertifiedDto;
import ywphsm.ourneighbor.controller.form.MemberForm;
import ywphsm.ourneighbor.controller.form.PhoneNumForm;
import ywphsm.ourneighbor.entity.Gender;
import ywphsm.ourneighbor.entity.Member;
import ywphsm.ourneighbor.service.MemberService;
import ywphsm.ourneighbor.service.login.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/sign_up")
public class SignController {

    private final MemberService memberService;

    @ModelAttribute("genders")
    public Gender[] genders() {
        return Gender.values();
    }

    @GetMapping
    public String SignUp(@ModelAttribute MemberForm memberForm) {
        return "sign/signUpForm";
    }

    @PostMapping
    public String saveMember(@Valid @ModelAttribute MemberForm memberForm,
                             BindingResult bindingResult,
                             @SessionAttribute(name = SessionConst.CERTIFIED_NUMBER) CertifiedDto certifiedDto) {

        if (!memberForm.getPassword().equals(memberForm.getPasswordCheck())) {
            bindingResult.reject("passwordCheck");
        }


        if (memberService.doubleCheck(memberForm.getUsername())) {
            bindingResult.reject("doubleCheck", new Object[]{memberForm.getUsername()}, null);
        }

        if (certifiedDto != null) {
            if (!certifiedDto.getCertifiedNumber().equals(memberForm.getCertifiedNumber())) {
                bindingResult.reject("certifiedNumber");
            }
            if (!certifiedDto.getPhoneNumber().equals(memberForm.getPhoneNumber())) {
                bindingResult.reject("certifiedPhoneNumber");
            }
            if (memberService.PhoneDoubleCheck(certifiedDto.getPhoneNumber())) {
                bindingResult.reject("PhoneDoubleCheck", new Object[]{certifiedDto.getPhoneNumber()}, null);
            }
        }



        if (bindingResult.hasErrors()) {
            return "sign/signUpForm";
        }

        //패스워드 암호화
        String encodedPassword = memberService.encodedPassword(memberForm.getPassword());

        Member member = new Member(memberForm.getUsername(), memberForm.getAge(), memberForm.getPhoneNumber(),
                memberForm.getGender(), memberForm.getLoginId(), encodedPassword,
                memberForm.getEmail(), memberForm.getName()
        );

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/certified")
    public @ResponseBody String certifiedNumber(@ModelAttribute PhoneNumForm phoneNumForm, HttpServletRequest request) {

        Random random = new Random();
        String certifiedNumber = String.valueOf(random.nextInt(10000));

        log.info("phoneNumber {}", phoneNumForm.getPhoneNumber());
        log.info("randomNum {}", certifiedNumber);
        memberService.certifiedPhoneNumber(phoneNumForm.getPhoneNumber(), certifiedNumber);

        CertifiedDto certifiedDto = new CertifiedDto();
        certifiedDto.setCertifiedNumber(certifiedNumber);
        certifiedDto.setPhoneNumber(phoneNumForm.getPhoneNumber());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.CERTIFIED_NUMBER, certifiedDto);

        return "ok";
//        return "redirect:/sign_up";
    }

    @GetMapping("/certified_phone")
    public String signUpPhoneNum(@ModelAttribute PhoneNumForm phoneNumForm) {
        return "sign/phoneCertifiedForm";
    }


}
