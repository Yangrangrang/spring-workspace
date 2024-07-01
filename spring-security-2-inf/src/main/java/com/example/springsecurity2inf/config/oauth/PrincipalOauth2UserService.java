package com.example.springsecurity2inf.config.oauth;

import com.example.springsecurity2inf.config.auth.PrincipalDetails;
import com.example.springsecurity2inf.model.User;
import com.example.springsecurity2inf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    @Lazy // TODO: PasswordEncoder를 Autowired하게 되면 순환의존관계 오류 발생 임시로 어노테이션 사용. 추후 다른 방법도 확인해보자.
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    // 구글로 부터 받은 userRequest데이터에 대한  후처리 되는 함수
    // 함수 종료 시, @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest: " + userRequest);
        System.out.println("userRequest.getClientRegistration() = " + userRequest.getClientRegistration()); // registrational로 어떤 OAuth로 로그인했는 지 확인 가능
        System.out.println("userRequest.getAccessToken().getTokenValue() = " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        // 구글 로그인버튼 클릭 ->  구글 로그인창 -> 로그인을 완료 -> code 반환(OAuth-Client라이브러리) -> AccessToken요청
        // userRequest정보 -> loadUser함수 호출 -> 구글로 부터 회원프로필 받아준다.
        System.out.println("super.loadUser(userRequest).getAttributes() = " + super.loadUser(userRequest).getAttributes());

        String provider = userRequest.getClientRegistration().getClientId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId;  // google_2130198i43290532
        String password = passwordEncoder.encode("겟인데어");
        String email = oAuth2User.getAttribute("email");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            System.out.println("구글 로그인이 최초입니다.");
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            userRepository.save(userEntity);
        } else {
            System.out.println("구글 로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어있습니다.");
        }

        // 회원가입을 강제로 진행해볼 예정
        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
