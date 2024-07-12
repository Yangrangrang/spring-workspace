package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.config.jwt.JwtAuthenticationFilter;
import com.example.springsecurityjwt.config.jwt.JwtAuthorizationFilter;
import com.example.springsecurityjwt.filter.MyFilter1;
import com.example.springsecurityjwt.filter.MyFilter3;
import com.example.springsecurityjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    // authenticaionManager 가 null 에러 이슈
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);   // 내서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정 하는 것
        config.addAllowedOrigin("*");   // 모든 IP에 응담을 허용한다.
        config.addAllowedHeader("*");   // 모든 header에 응답을 허용하겠다.
        config.addAllowedMethod("*");   // 모든 post,get,put,delete,patch 요청을 허용하겠다.
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//        http.addFilterBefore(new MyFilter1(), BasicAuthenticationFilter.class);   // 필터 걸때 이렇게 할 필요는 없다.
//        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class); // 시큐리티 필터가 우선.
        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable())
                .addFilter(new JwtAuthenticationFilter(authenticationManager))   // AuthenticationManager 를 던져줘야함.
                .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/user/**").hasAnyAuthority("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN")
                        .requestMatchers("/api/v1/manager/**").hasAnyAuthority("ROLE_MANAGER", "ROLE_ADMIN")
                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
