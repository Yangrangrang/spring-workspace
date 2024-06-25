package com.example.springsecurity2inf.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    // 구글로 부터 받은 userRequest데이터에 대한  후처리 되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: " + userRequest);
        System.out.println("userRequest.getClientRegistration() = " + userRequest.getClientRegistration()); // registrational로 어떤 OAuth로 로그인했는 지 확인 가능
        System.out.println("userRequest.getAccessToken().getTokenValue() = " + userRequest.getAccessToken().getTokenValue());
        // 구글 로그인버튼 클릭 ->  구글 로그인창 -> 로그인을 완료 -> code 반환(OAuth-Client라이브러리) -> AccessToken요청
        // userRequest정보 -> loadUser함수 호출 -> 구글로 부터 회원프로필 받아준다.
        System.out.println("super.loadUser(userRequest).getAttributes() = " + super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 회원가입을 강제로 진행해볼 예정
        return super.loadUser(userRequest);
    }
}
