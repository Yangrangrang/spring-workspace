package com.example.springsecurity1.service;

import com.example.springsecurity1.domain.UserAccount;
import com.example.springsecurity1.dto.UserAccountDto;
import com.example.springsecurity1.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository repository;

    public UserAccountDto savedUser(UserAccount entity) {
        UserAccount user = repository.save(entity);
        return UserAccountDto.from(user);
    }

    public UserAccountDto getUser(Long id) {
        UserAccount user = repository.findById(id).orElse(null);
        return UserAccountDto.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = repository.findByUserId(username);

        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
        }

        // 사용자 정보를 UserDatails 객체로 변환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getUserPassword())
                .roles("USER")
                .build();
    }
}
