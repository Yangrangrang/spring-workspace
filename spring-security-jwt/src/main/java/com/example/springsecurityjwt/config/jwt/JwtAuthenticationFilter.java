package com.example.springsecurityjwt.config.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음.
 * /login 으로 요청을 해서 username , password를 전송하면 (post)
 * UsernamePasswordAuthenticationFilter 동작을 함.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JWTAuthenticationFilter : 로그인 시도중");

        // 1. username, password 받아서

        // 2. 정상인지 로그인 시도를 해보는 거에요. authenticationManager 로 로그인 시도를 하면
        // PrincipalDetailsService 가 호출되서 자동으로 loadUserByUsername() 함수 실행됨.

        // 3. PrincipalDetails 를 세션에 담고 (세션에 담지 않으면 권한 관리가 되지 않음..권한 관리를 위해서 이기때문에 권한이 따로 없다면 세션에 안담아도 됨.)

        // 4. JWT 토킁을 만들어서 응답해주면 됨.
        return super.attemptAuthentication(request, response);
    }
}
