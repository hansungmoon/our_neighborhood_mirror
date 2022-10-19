package ywphsm.ourneighbor;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.security.MemberDetailsImpl;
import ywphsm.ourneighbor.service.login.SessionConst;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

// Spring Data JPA에서 Auditing 기능 사용을 위해 필수 (등록, 수정 추적)
@EnableJpaAuditing
@SpringBootApplication
public class OurNeighborApplication {

	// AWS 메타데이터 로딩으로 인한 서비스 연결 시점의 지연을 막기 위해
	// disable 옵션을 true로 선언
	// 이걸 키면 credentials.instanceProfile을 불러오지 못함
	static {
		System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
	}

	public static void main(String[] args) {
		SpringApplication.run(OurNeighborApplication.class, args);
	}

	// JPAQueryFactory 스프링 빈으로 등록
	@Bean
	JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
		return new JPAQueryFactory(entityManager);
	}

	// 이게 있어야 작성자 수정자 받을 수 있음
	// UUID 부분은 실무에선 session에서 사용자 아이디를 받아와서 처리
	@Bean
	public AuditorAware<Long> auditorProvider(HttpServletRequest request) {
		return new AuditorAware<Long>() {
			@Override
			public Optional<Long> getCurrentAuditor() {
				HttpSession session = request.getSession();
				Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
				if (member == null) {
					return Optional.empty();
				}
				return Optional.of(member.getId());
			}
		};

	}
}
