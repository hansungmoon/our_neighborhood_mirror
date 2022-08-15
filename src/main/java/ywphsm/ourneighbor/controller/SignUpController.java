package ywphsm.ourneighbor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ywphsm.ourneighbor.controller.form.MemberForm;
import ywphsm.ourneighbor.controller.form.PhoneCertifiedForm;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.service.MemberService;
import ywphsm.ourneighbor.service.email.TokenService;
import ywphsm.ourneighbor.service.login.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/sign_up")
public class SignUpController {

    private final MemberService memberService;
    private final TokenService tokenService;


    @GetMapping("/home")
    public String signUpHome() {
        return "login/login";
    }

    @GetMapping
    public String signUp(@ModelAttribute MemberForm memberForm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(SessionConst.PHONE_CERTIFIED);

        if (attribute == null) {
            return "redirect:/sign_up/certifiedPhone";
        }

        return "signUp/signUpForm";
    }

    @PostMapping
    public String signUp(@Valid @ModelAttribute MemberForm memberForm,
                         BindingResult bindingResult,
                         @SessionAttribute(name = SessionConst.PHONE_CERTIFIED) PhoneCertifiedForm certifiedForm) {

        if (!memberForm.getPassword().equals(memberForm.getPasswordCheck())) {
            bindingResult.reject("passwordCheck");
        }

        if (memberService.doubleCheck(memberForm.getUsername()) != null) {
            bindingResult.reject("doubleCheck", new Object[]{memberForm.getNickname()}, null);
        }

        if (!memberForm.getPhoneNumber().equals(certifiedForm.getPhoneNumber()) &&
                !memberForm.getCertifiedNumber().equals(certifiedForm.getCertifiedNumber())) {
            bindingResult.reject("phoneCertifiedFail");
        }

        if (memberService.findByPhoneNumber(memberForm.getPhoneNumber()) != null) {
            bindingResult.reject("phoneDoubleCheck");
        }

        if (memberService.findByEmail(memberForm.getEmail()) != null) {
            bindingResult.reject("emailDoubleCheck");
        }

        if (bindingResult.hasErrors()) {
            return "signUp/signUpForm";
        }

        int age = memberService.ChangeBirthToAge(memberForm.getBirthDate());

        //패스워드 암호화
        String encodedPassword = memberService.encodedPassword(memberForm.getPassword());

        Member member = new Member(memberForm.getUsername(), memberForm.getBirthDate(),
                age, memberForm.getPhoneNumber(),
                memberForm.getGender(), memberForm.getUserId(), encodedPassword,
                memberForm.getEmail(), memberForm.getNickname());

        memberService.join(member);
        tokenService.createEmailToken(member.getId(), member.getEmail());

        return "signUp/confirmEmail";
    }

    @GetMapping("/confirm-email")
    public String viewConfirmEmail(@RequestParam String token) {
        if (memberService.confirmEmail(token)) {
            return "redirect:/login";
        }

        return "signUp/emailFail";
    }

    @GetMapping("/certifiedPhone")
    public String certifiedPhone(@ModelAttribute PhoneCertifiedForm phoneCertifiedForm) {
        return "signUp/certifiedPhoneForm";
    }
    @PostMapping("/certifiedPhone")
    public String certifiedPhone(@Valid @ModelAttribute PhoneCertifiedForm phoneCertifiedForm,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signUp/certifiedPhoneForm";
        }

        return "redirect:/sign_up/sendSMS?phoneNumber=" + phoneCertifiedForm.getPhoneNumber();
    }

    @GetMapping("/sendSMS")
    public String sendSMS(@RequestParam String phoneNumber, HttpServletRequest request) {
        Random rand = new Random();
        String certifiedNumber = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            certifiedNumber += ran;
        }

        log.info("수신자 번호:{}", phoneNumber);
        log.info("인증 번호:{}", certifiedNumber);
        memberService.certifiedPhoneNumber(phoneNumber, certifiedNumber);

        PhoneCertifiedForm certifiedForm = new PhoneCertifiedForm();
        certifiedForm.setPhoneNumber(phoneNumber);
        certifiedForm.setCertifiedNumber(certifiedNumber);

        HttpSession session = request.getSession();
        session.setAttribute("phoneCertified", certifiedForm);

        return "redirect:/sign_up";
    }
}