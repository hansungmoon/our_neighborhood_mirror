package ywphsm.ourneighbor.repository.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.member.QMember;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * 탈퇴예정 시간이 현재시간을 지난 회원을 모두 조회
     */
    @Override
    public List<Member> findWithdrawalMember(LocalDateTime now) {
        return queryFactory
                .selectFrom(QMember.member)
                .where(withdrawalTimeLess(now),
                        withdrawalTure())
                .fetch();

    }

    private BooleanExpression withdrawalTimeLess(LocalDateTime now) {
        return now != null ? QMember.member.withdrawalTime.before(now) : null;
    }

    private BooleanExpression withdrawalTure() {
        return QMember.member.withdrawal != null ? QMember.member.withdrawal.isTrue() : null;
    }
}
