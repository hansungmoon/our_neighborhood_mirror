package ywphsm.ourneighbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ywphsm.ourneighbor.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsername(String username);

    Optional<Member> findByLoginId(String loginId);

    List<Member> findByPhoneNumber(String phoneNumber);
}
