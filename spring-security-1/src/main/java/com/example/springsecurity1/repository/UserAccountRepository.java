package com.example.springsecurity1.repository;

import com.example.springsecurity1.domain.UserAccount;
import com.example.springsecurity1.dto.UserAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    public Optional<UserAccountDto> findByUserId(String id);

}
