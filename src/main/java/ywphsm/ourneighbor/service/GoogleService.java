package ywphsm.ourneighbor.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
<<<<<<< HEAD
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
=======
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
import ywphsm.ourneighbor.controller.form.google.GoogleOAuthRequest;
import ywphsm.ourneighbor.controller.form.google.GoogleOAuthResponse;
import ywphsm.ourneighbor.domain.member.Member;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleService {

    private final MemberService memberService;

    @Value("${google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;
    @Value("${google.redirect.uri}")
    private String GOOGLE_SNS_REDIRECT_URL;
    @Value("${google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
    @Value("${google.token.url}")
    private String GOOGLE_SNS_TOKEN_BASE_URL;

<<<<<<< HEAD
    public Member getUserInfo(String code, Model model) throws JsonProcessingException {
=======
    public Member getUserInfo(String code, Model model) throws JsonProcessingException, ParseException {
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97

        //Google OAuth Access Token 요청을 위한 파라미터 세팅
        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
                .builder()
                .clientId(GOOGLE_SNS_CLIENT_ID)
                .clientSecret(GOOGLE_SNS_CLIENT_SECRET)
                .code(code)
                .redirectUri(GOOGLE_SNS_REDIRECT_URL)
                .grantType("authorization_code").build();

        log.info("googleOAuthRequest:{}", googleOAuthRequest);

        //HTTP Request를 위한 RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        //JSON 파싱을 위한 기본값 세팅
        //요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
<<<<<<< HEAD
        log.info("ObjectMapper");
=======
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97

        //AccessToken 발급 요청
        ResponseEntity<String> resultEntity = restTemplate.postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, googleOAuthRequest, String.class);
        log.info("resultEntity:{}", resultEntity);

        //Token Request
        GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {
        });

<<<<<<< HEAD
        //ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)
        String jwtToken = result.getIdToken();
        String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo")
                .queryParam("id_token", jwtToken).encode().toUriString();
        log.info("requestUrl:{}", requestUrl);

        String resultJson = restTemplate.getForObject(requestUrl, String.class);
        log.info("resultJson:{}", resultJson);

        Map<String,String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>(){});
        log.info("userInfo={}", userInfo);

        model.addAllAttributes(userInfo);
        model.addAttribute("token", result.getAccessToken());

        return saveGoogleUser(model);
    }

    public Member saveGoogleUser(Model model) {

        String name = String.valueOf(model.getAttribute("name"));
        String email = String.valueOf(model.getAttribute("email"));
        boolean email_verified = model.getAttribute("email_verified").equals("true");

        Member member = new Member(email, name, email_verified);
=======
        String access_token = result.getAccessToken();
        log.info("accessToken = {}", access_token);

        //프로필 요청 헤더 작성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + access_token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //프로필정보 요청
        ResponseEntity<String> googleUserInfo = restTemplate.exchange(
                "https://people.googleapis.com/v1/people/me?personFields=genders,names,emailAddresses,phoneNumbers",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );
        log.info("naverUserInfo:{}", googleUserInfo);

        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(googleUserInfo.getBody());

        String name = parse.getAsJsonObject().get("names").getAsJsonArray().get(0).getAsJsonObject().get("displayName").getAsString();
        String genders = parse.getAsJsonObject().get("genders").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString();
        String email = parse.getAsJsonObject().get("emailAddresses").getAsJsonArray().get(0).getAsJsonObject().get("value").getAsString();
        String emailVerified = parse.getAsJsonObject().get("emailAddresses").getAsJsonArray().get(0).getAsJsonObject().get("metadata").getAsJsonObject().get("verified").getAsString();

        log.info("name:{}", name);
        log.info("gender:{}", genders);
        log.info("email:{}", email);
        log.info("emailVerified:{}", emailVerified);
        boolean email_verified = emailVerified.equals("true");
        int gender = genders.equals("male") ? 0 : 1;

        Member member = new Member(email, name, email_verified, gender);
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
        Member findMember = memberService.findByEmail(email);
        if (findMember == null) {
            memberService.join(member);
        }

        return member;
<<<<<<< HEAD

    }


=======
    }
>>>>>>> 118caa3cd9b991f0dd390a415068ce56a3133f97
}
