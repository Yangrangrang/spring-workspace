package com.example.springsecurity1.service;

import com.example.springsecurity1.domain.UserAccount;
import com.example.springsecurity1.dto.UserAccountDto;
import com.example.springsecurity1.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountDto savedUser(UserAccount entity) {
        UserAccount user = repository.save(entity);
        return UserAccountDto.from(user);
    }

    public UserAccountDto getUser(Long id) {
        UserAccount user = repository.findById(id).orElse(null);
        return UserAccountDto.from(user);
    }
}
