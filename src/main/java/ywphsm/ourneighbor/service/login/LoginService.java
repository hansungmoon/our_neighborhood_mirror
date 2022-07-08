package ywphsm.ourneighbor.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ywphsm.ourneighbor.entity.Member;
import ywphsm.ourneighbor.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {

        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);

    }
}
