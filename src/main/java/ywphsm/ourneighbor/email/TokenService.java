package ywphsm.ourneighbor.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {

    private final EmailTokenRepository emailTokenRepository;
    private final EmailService emailService;

    public String createEmailToken(Long memberId, String receiverEmail) {

        EmailToken emailToken = EmailToken.createEmailToken(memberId);
        emailTokenRepository.save(emailToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiverEmail);   //보낼 이메일주소 추가
        mailMessage.setSubject("회원가입 이메일 인증");  //제목
        mailMessage.setText("http://localhost:8080/confirm-email?token=" + emailToken.getId());//URL+ 토큰ID
        emailService.sendEmail(mailMessage);

        return emailToken.getId();
    }

    //유효한 토큰 가져오기
    public EmailToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId){
        Optional<EmailToken> confirmationToken = emailTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(), false);
        return confirmationToken.orElse(null);
//        return confirmationToken.orElseThrow(()-> new BadRequestException(ValidationConstant.TOKEN_NOT_FOUND));
    };
}
