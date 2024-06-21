package com.example.springsecurity2inf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // 스피링 시큐리티 필터가 스프링 필터체인에 등록됨.
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
//                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/manager/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER")
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((login) -> login
                        .loginPage("/loginForm")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/")
                );

        return http.build();
    }
}
