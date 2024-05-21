package com.example.springsecurity1.config;

import com.example.springsecurity1.service.UserAccountService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

    private final UserAccountService userAccountService;

    public DefaultSecurityConfig(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    /**
     * spring security 공식홈페이지에서 확인.
     * 내장 사용 관리자를 상ㅇ하여 기본적인 사용자를 메모리에 저장하고, 이 사용자를 인증에 활용하는 예시를 보여줌.
     * 간단 테스트용, 개발 환경에서 사용
     */
//    @Bean
//    @ConditionalOnMissingBean(UserDetailsService.class)
//    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        String generatedPassword = passwordEncoder().encode("123");
//        return new InMemoryUserDetailsManager(User.withUsername("user")
//                .password(generatedPassword).roles("USER").build());
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
//                .csrf(csrf ->csrf.disable())    // csrd 비활성화 (개발환경에서는 사용 가능 운영X)
                .authorizeHttpRequests((authorize) -> authorize

                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/",
                                "/user/form"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/form").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/user/form")) // "/user/form"에 대한 CSRF 보호 비활성화
                // 해당 경로에 대한 POST 요청은 CSRF 토큰 검증을 거치지 않고 허용 , 운영 환경에서 csrf보호를 해제하는 것 보다 다른 방법 고려, ex) CAPTCHA, 이메일 인증
                .formLogin(Customizer.withDefaults())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")

//  세션관리 정책을 stateless로 설정 했기 때문에 모든 요청이 세션을 사용하지 않도록 만듬, 그래서 이전 요청과 독립적으로 처리 됨.(원래 요청인 url이 아니라 디폴트로 설정 된 페이지로 이동..)
//                        .invalidateHttpSession(true))
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(UserAccountService userAccountService) {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("123")
//                .roles("USER")
//                .build();
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userAccountService
                .searchUser(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.toEntity().getUserId())
                        .password(user.toEntity().getUserPassword())
                        .roles("USER")
                        .build()
                )
                .orElseThrow(()-> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    }
}
