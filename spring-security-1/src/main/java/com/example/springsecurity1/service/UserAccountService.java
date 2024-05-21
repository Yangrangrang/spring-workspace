package com.example.springsecurity1.service;

import com.example.springsecurity1.domain.UserAccount;
import com.example.springsecurity1.dto.UserAccountDto;
import com.example.springsecurity1.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserAccountService {

    private final UserAccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountService(@Lazy PasswordEncoder passwordEncoder, UserAccountRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public UserAccountDto savedUser(UserAccountDto dto) {
        UserAccount user = dto.toEntity(passwordEncoder);
        repository.save(user);
        return UserAccountDto.from(user);
    }

    public UserAccountDto getUser(Long id) {
        UserAccount user = repository.findById(id).orElse(null);
        return UserAccountDto.from(user);
    }

    @Transactional(readOnly = true)
    public Optional<UserAccountDto> searchUser(String username) {
        return repository.findByUserId(username);
    }
}
