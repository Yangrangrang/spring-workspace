package com.example.springsecurity2inf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // 스피링 시큐리티 필터가 스프링 필터체인에 등록됨.
// deprecated됨. EnableMethodSecurity 를 대신 사용하라고 함.
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true) // securedEnabled활성화, preAuthorize, postAuthorize 활성화
public class SecurityConfig {

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
                );

        return http.build();
    }
}
