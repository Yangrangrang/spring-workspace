package com.example.springsecurity2inf.config;

import com.example.springsecurity2inf.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * OAuth2.0 google
 * 1. 코드받기(인증) 2. 엑세스토큰(권한) 3. 사용자프로필 정보들 가져오고
 * 4-1. 그 정보를 토대로 회원가입을 자동으로 진행시키기도 함.
 * 4-2. (이메일,전화번호, 이름, 아이디) 쇼필몰 -> (집주소), 백화점몰 -> (vip 등급, 일반등급)
 */

@Configuration
@EnableWebSecurity  // 스피링 시큐리티 필터가 스프링 필터체인에 등록됨.
// @EnableGlobalMethodSecurity -> deprecated됨. EnableMethodSecurity 를 대신 사용하라고 함.
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true) // securedEnabled활성화, preAuthorize, postAuthorize 활성화
public class SecurityConfig {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "join", "joinProc", "joinForm", "loginForm").permitAll()
                        .requestMatchers("/user/**").authenticated()    // 인증만 되면 들어갈 수 있는 주소!
                        .requestMatchers("/manager/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/login")   // login주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해줌.
                        .defaultSuccessUrl("/")
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/loginForm")
                        .userInfoEndpoint(userInfo -> userInfo.userService(principalOauth2UserService))
                ); // 구글 로그인이 완료된 뒤의 후처리가 필요함. Tip. 코드X, (엑세스토큰+사용자프로필정보O)

        return http.build();
    }
}
