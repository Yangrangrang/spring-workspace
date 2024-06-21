package com.example.springsecurity2inf.repository;

import com.example.springsecurity2inf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수 JpaRepository 가 들고있음.
public interface UserRepository extends JpaRepository<User, Integer> {
}
