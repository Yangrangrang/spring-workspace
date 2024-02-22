package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {

        /**
         * SecurityContextHolder : 시큐리티에 대한 정보를 모두 들고 있는 클래스
         * getContext : securityContext 를 불러옴
         * securitycontext에는 authentication정보가 있고
         * 이것이 인증이 되었는지 로그인 한 상태인지 확인 isAuthenticated
         * 로그인 정보인 principal을 끌어옴  (보편적인 pricipal : java.security interface)
         * 관련된 구현체를 BoardPrincipal로 만들었는데 getPrincipal 이 받아들이게 되는건 userDetail 인터페이스 구현체
         * 그래서 boardPrincipal 을 불러올 수 있다.
         * BoardPrincipal.class::cast 로 타입캐스팅이 성공한다.
         * 그러면 getUserName을 불러와서 실제로 user 정보를 불러옴
         */

        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(BoardPrincipal.class::cast)
                .map(BoardPrincipal::getUsername);
    }
}
