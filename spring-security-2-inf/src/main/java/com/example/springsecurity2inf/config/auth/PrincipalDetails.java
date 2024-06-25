package com.example.springsecurity2inf.config.auth;

import com.example.springsecurity2inf.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * 시큐리티가 /login 주소요청이 오면 낚아채서 로그인을 진행시킨다.
 * 로그인 진행이 완료되면 session 을 만들어준다. (Security ContextHolder)
 * 시큐리티가 가지고 있는 세션에 들어갈 수 있는 오브젝트가 정해져 있다.
 * 오브젝트 => Authentication 타입 객체
 * Authentication 안에 User 정보가 있어야함.
 * User 오브젝트타입 => UserDetail 타입 객체
 *
 * Security Session => Authentication => UserDetail(PrincipalDetails)
 */

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user;  // 콤포지션

    public PrincipalDetails(User user) {
        this.user = user;
    }

    // 해당 User의 권한을 리턴하는 곳!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }
    // 계정 잠겼네

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    // 계정활성화

    @Override
    public boolean isEnabled() {

        // 1년동안 회원이 로그인을 안하면 휴먼계정으로 하기로 함.
        // 현재시간 - 로그인시간 => 1년 초과시, return false

        return UserDetails.super.isEnabled();
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }
}
