package ywphsm.ourneighbor.unit.service;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import ywphsm.ourneighbor.domain.dto.Member.MemberDTO;
import ywphsm.ourneighbor.domain.dto.ReviewDTO;
import ywphsm.ourneighbor.domain.file.AwsS3FileStore;
import ywphsm.ourneighbor.domain.file.UploadFile;
import ywphsm.ourneighbor.domain.member.Member;
import ywphsm.ourneighbor.domain.store.Review;
import ywphsm.ourneighbor.domain.store.Store;
import ywphsm.ourneighbor.repository.member.MemberRepository;
import ywphsm.ourneighbor.service.ReviewService;
import ywphsm.ourneighbor.service.member.MemberService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {


    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @Spy
    @InjectMocks
    AwsS3FileStore awsS3FileStore;

//    @Spy
//    @InjectMocks
//    Environment environment;

    @Test
    @DisplayName("회원 저장")
    void should_SaveMember() throws IOException {
        // given
        MockMultipartFile file = new MockMultipartFile("file", "test.png", "image/png",
                new FileInputStream("C:/Users/HOME/Desktop/JAVA/menu_file/5cf53790-54a5-4c5f-9709-0394d58cec94.png"));

        MemberDTO.Add dto = MemberDTO.Add.builder()
                .birthDate("19991002")
                .password("1234")
                .file(file)
                .build();

        Member member = dto.toEntity(25, "1234encode");

        Long mockMemberId = 1L;
        setField(member, "id", mockMemberId);

        UploadFile uploadFile = new UploadFile("업로드명1", "저장명1", "URL1");

        given(awsS3FileStore.storeFile(dto.getFile())).willReturn(uploadFile);
        given(memberRepository.save(any())).willReturn(member);

        // when
        Long resultId = memberService.save(dto);

        // then
        then(awsS3FileStore).should().storeFile(dto.getFile());
        then(memberRepository).should().save(any());
    }
}
