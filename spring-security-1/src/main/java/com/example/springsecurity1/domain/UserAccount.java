package com.example.springsecurity1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class UserAccount extends AuditingFields{

    @Id
    @GeneratedValue
    @Column(name = "userIdx")
    private Long id;

    private String userId;
    private String userPassword;
    private String nickName;
    private String email;
    private String phone;
    private String isDelete;
    private LocalDateTime lastLogin;

}
