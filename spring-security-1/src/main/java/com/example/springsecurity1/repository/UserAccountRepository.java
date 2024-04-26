package com.example.springsecurity1.repository;

import com.example.springsecurity1.dto.UserAccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountDto, Long> {

}
