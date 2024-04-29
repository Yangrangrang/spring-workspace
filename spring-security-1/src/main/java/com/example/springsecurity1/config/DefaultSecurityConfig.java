package com.example.springsecurity1.config;

import com.example.springsecurity1.domain.UserAccount;
import com.example.springsecurity1.dto.UserAccountDto;
import com.example.springsecurity1.repository.UserAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@EnableWebSecurity
@Configuration
public class DefaultSecurityConfig {

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
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(HttpMethod.GET,"/").permitAll()
                        .anyRequest().authenticated())
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
//    public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("123")
//                .roles("USER")
//                .build();
//    }
}
