package ywphsm.ourneighbor.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ywphsm.ourneighbor.service.KakaoService;
import ywphsm.ourneighbor.service.MemberService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/sign_in/kakao")
    public String kakaoIndex(@RequestParam String code) {
        log.info("code={}", code);

        kakaoService.getKaKaoAccessToken(code);
        return "kakao/kakaoIndex";
    }

}
