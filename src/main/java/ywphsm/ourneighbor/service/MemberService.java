package ywphsm.ourneighbor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ywphsm.ourneighbor.entity.Gender;
import ywphsm.ourneighbor.entity.Member;
import ywphsm.ourneighbor.repository.MemberRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;

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
}