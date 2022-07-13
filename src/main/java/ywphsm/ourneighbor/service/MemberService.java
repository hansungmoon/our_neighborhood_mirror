package ywphsm.ourneighbor.service;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ywphsm.ourneighbor.email.EmailToken;
import ywphsm.ourneighbor.email.TokenService;
import ywphsm.ourneighbor.entity.Gender;
import ywphsm.ourneighbor.entity.Member;
import ywphsm.ourneighbor.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;

    private final TokenService tokenService;

    //회원가입
    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    //닉네임 중복체크
    public boolean doubleCheck(String username) {
        List<Member> findMembers = memberRepository.findByUsername(username);
        return !findMembers.isEmpty();
    }

    //휴대폰 번호 중복체크
    public boolean PhoneDoubleCheck(String phoneNumber) {
        List<Member> findMembers = memberRepository.findByPhoneNumber(phoneNumber);
        return !findMembers.isEmpty();
    }


    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 한명 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //회원가입시 비밀번호 암호화
    public String encodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    //비밀번호 맞는지 확인
    public Boolean passwordCheck(String password, String beforePassword) {

        return passwordEncoder.matches(beforePassword, password);
    }


    //회원 수정 변경 감지
    public void update(Long id, String username, Integer age, String phoneNumber,
                       String email, String name) {

        Member member = memberRepository.findById(id).get();

        member.update(username, age, phoneNumber, email, name);
        em.flush();
        em.clear();
    }

    //회원 수정 변경 감지(비밀번호)
    public void updatePassword(Long id, String password) {

        Member member = memberRepository.findById(id).get();

        member.updatePassword(password);
        em.flush();
        em.clear();
    }

    //문자 휴대폰 본인인증
    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "NCS0EUZ1M5AZKUFA";
        String api_secret = "YOHXWEBYGAWYTGPCTUXKXJOEZOF7VYKZ";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01038352375");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "핫띵크 휴대폰인증 테스트 메시지 : 인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

    //이메일 인증 로직
    public void confirmEmail(String tokenId) {
        EmailToken findToken = tokenService.findByIdAndExpirationDateAfterAndExpired(tokenId);
        findToken.useToken();
        Member member = findOne(findToken.getMemberId()).orElse(null);
//        if (member != null) {
//            member.updateEmailCertified();
//        }
    }
}