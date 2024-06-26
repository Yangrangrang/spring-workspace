package com.example.springsecurity2inf.config.auth;

import com.example.springsecurity2inf.model.User;
import com.example.springsecurity2inf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 시큐리티 설정에서 로그인 .loginProcessingUrl("/login");
 * /login 요청이 오면 자동으로 UserDetailsService 타입으로 loc 되어 있는 loadUserByUsername 함수가 실행
 * 함수 종료 시, @AuthenticationPrincipal 어노테이션이 만들어진다.
 */
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        User userEntity = userRepository.findByUsername(username);
        System.out.println("userEntity = " + userEntity.getUsername());
        if (userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
