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

    public UserAccount() {}
    public UserAccount(String userId, String userPassword, String nickName, String email, String phone, String isDelete, LocalDateTime lastLogin) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;
        this.isDelete = isDelete;
        this.lastLogin = lastLogin;
    }

    public static UserAccount of(String userId, String userPassword, String nickName, String email, String phone, String isDelete, LocalDateTime lastLogin) {
        return new UserAccount(userId, userPassword, nickName, email, phone, isDelete, lastLogin);
    }
}
