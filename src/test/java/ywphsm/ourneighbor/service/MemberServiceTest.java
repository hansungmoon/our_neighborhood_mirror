package ywphsm.ourneighbor.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ywphsm.ourneighbor.config.AuditingConfig;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.member.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ywphsm.ourneighbor.domain.member.QMember.*;

@SpringBootTest(webEnvironment = SpringBootTest
        .WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
class MemberServiceTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @Autowired
    MemberService memberService;

    @LocalServerPort
    private int port;

    @BeforeEach
    void before() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    @DisplayName("회원 가입")
    void join() {
        Member member4 = new Member("kkk", "kkk", "user4",
                "유저4", "localhost@naver.com", "010-1234-1234", 19, 0);

        Long memberId = memberService.join(member4);

        Member findMember = memberService.findById(memberId);

        assertThat(memberId).isEqualTo(findMember.getId());
    }

    @Test
    @DisplayName("10대, 20대인 회원 찾기")
    void findMembers1020() {
        List<Member> memberList = queryFactory
                .select(member)
                .from(member)
                .where(member.age.goe(10).and(member.age.lt(30)))
                .fetch();

        assertThat(memberList).extracting("username").contains("박양원", "문한성");
    }

    @Test
    @DisplayName("멤버 Role 조회하기")
    void findRole() {
        String code = "판매자";

        Role role = Role.of(code);
        System.out.println("role.getKey() = " + role.getKey());
        System.out.println("role.getTitle() = " + role.getTitle());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("계정의 권한 업데이트")
    void updateRole() throws Exception {
        String userId = "ywonp94";
        String role = "판매자";

        Member findMember = memberService.findByUserId(userId);

        assertThat(findMember.getRole()).isEqualTo(Role.ADMIN);

        String url = "http://localhost:" + port + "/admin/update_role/" + findMember.getId();

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(role))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(findMember.getRole()).isEqualTo(Role.SELLER);
    }
}
















