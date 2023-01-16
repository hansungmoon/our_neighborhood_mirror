package ywphsm.ourneighbor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.repository.member.MemberRepository;
import ywphsm.ourneighbor.service.member.MemberReviewService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledService {

    private final MemberRepository memberRepository;

    private final MemberReviewService memberReviewService;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24) //하루마다 반복
    public void withdrawal() {
        List<Member> memberList = memberRepository.findAllByWithdrawalTimeIsBefore(LocalDateTime.now());
        for (Member member : memberList) {
            memberReviewService.withdrawal(member.getId());
        }

    }
}
