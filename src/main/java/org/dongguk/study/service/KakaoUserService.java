package org.dongguk.study.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.Repository.UserRepository;
import org.dongguk.study.domain.User;
import org.dongguk.study.dto.SocialUserInfoDto;
import org.dongguk.study.util.ELoginProvider;
import org.dongguk.study.util.EUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class KakaoUserService {
    @Autowired
    private UserRepository userRepository;
    @Value("${client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;
    @Value("${client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URI;
    @Value("${client.provider.kakao.user-info-uri}")
    private String KAKAO_USER_INFO_URI;


    public SocialUserInfoDto kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        // Authentication code로 access token 요청
        String accessToken = getKaKaoAccessToken(code);

        // access token으로 카카오 API 호출
        SocialUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

        // 카카오 ID로 회원가입 처리
        User kakaoUser = registerKakaoUser(kakaoUserInfo);

        return kakaoUserInfo;
    }

    private String getKaKaoAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", KAKAO_CLIENT_ID);   // kakao developers에서 제공된 REST API키
        body.add("redirect_uri", KAKAO_REDIRECT_URI);  // redirect할 callback uri
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                KAKAO_TOKEN_URI,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    private SocialUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                KAKAO_USER_INFO_URI,
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        // responseBody에 있는 정보를 꺼냄
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        Long id = jsonNode.get("id").asLong();
        String email = jsonNode.get("kakao_account").get("email").asText();
        String name = jsonNode.get("properties")
                .get("name").asText();

        return new SocialUserInfoDto(id, name, email);
    }

    private User registerKakaoUser(SocialUserInfoDto kakaoUserInfo) {
        // DB 에 중복된 email이 있는지 확인
        String kakaoEmail = kakaoUserInfo.getEmail();
        String name = kakaoUserInfo.getNickName();
        User kakaoUser = userRepository.findByUserEmail(kakaoEmail).orElse(null);

        if (kakaoUser == null) {
            // 회원가입
            kakaoUser = new User(kakaoEmail, ELoginProvider.KAKAO, EUserRole.USER, name);
            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }
}
