package com.example.springsecurityjwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springsecurityjwt.config.auth.PrincipalDetails;
import com.example.springsecurityjwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

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
        try {
            /**
             BufferedReader br = request.getReader();

             String input = null;
             while ((input = br.readLine()) != null) {
             System.out.println("input = " + input);  // username=test&password=1234
             }
             */

            // 위 방법 보다 더 편하게
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println("user = " + user);

            // 토큰 만들기
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            System.out.println("authenticationToken = " + authenticationToken);

            // PrincipalDetailsService 의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication 이 리턴 됨.
            // DB 에 있는 username 과 password 가 일치
            // authentication 에 내 로그인한 정보가 담김.
            System.out.println("authenticationManager = " + authenticationManager);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // authentication 객체가 session 영역에 저장됨. => 로그인이 되었다는 뜻
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("principalDetails.getUsername = " + principalDetails.getUsername());
            System.out.println("principalDetails.getPassword = " + principalDetails.getPassword());

            return authentication;  // session 영역에 저장을 해야하고 그 방법이 return 해주면 됨.
            // 리턴의 이유는 권한 관리를 security 가 대신 해주기 때문에 편하려고 하는거임.
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 근데 단지 권한 처리 때문에 session 에 넣어줌.

//            System.out.println(request.getInputStream().toString());    // 유저네임과 패스워드가 담겨 있다.
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("===================================");

        // 2. 정상인지 로그인 시도를 해보는 거에요. authenticationManager 로 로그인 시도를 하면
        // PrincipalDetailsService 가 호출되서 자동으로 loadUserByUsername() 함수 실행됨.

        // 3. PrincipalDetails 를 세션에 담고 (세션에 담지 않으면 권한 관리가 되지 않음..권한 관리를 위해서 이기때문에 권한이 따로 없다면 세션에 안담아도 됨.)

        // 4. JWT 토킁을 만들어서 응답해주면 됨.
        return null;
    }

    /**
     * attemptAuthentication() 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행 됨.
     * JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨.
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행 됨 : 인증이 완료 되었다는 뜻.");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // RSA 방식이 아닌 Hash암호 방식
        String jwtToken = JWT.create()
                .withSubject("cos-token")
                .withExpiresAt(new Date(System.currentTimeMillis() + (60000*10)))    // 만료 시간 60000*10 : 10분
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos"));    // 내 서버만 아는 고유 시크릿

        response.addHeader("Authorization", "Bearer " + jwtToken);

        /**
         * 유저 네임, 패스워드 로그인 정상
         *
         * 서버 쪽 세션ID 생성
         * 클라이언트 쿠키 세션ID를 응답
         *
         * 요청 할 때마다 쿠키값 세션ID를 항상 들고 서버쪽으로 요청하기 때문에 서버는 세션ID가 유효한지 판단해서 유효하면 인증이 필요한 페이지로 접근하게 하면 됨.
         *
         * 유저 네임, 패스워드 로그인 정상
         * JWT 토큰 생성
         * 클라이언트 쪽으로 JWT 토큰을 응답
         *
         * 요청 할 때마다 JWT토큰을 가지고 요청
         * 서버는 JWT토큰이 유효한지를 판단 (필터를 만들어야함)
         */
    }
}
