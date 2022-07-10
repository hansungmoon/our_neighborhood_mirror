package ywphsm.ourneighbor.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ywphsm.ourneighbor.entity.Member;
import ywphsm.ourneighbor.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId)
                .filter(member -> passwordEncoder.matches(password, member.getPassword()))
                .orElse(null);

    }



}
