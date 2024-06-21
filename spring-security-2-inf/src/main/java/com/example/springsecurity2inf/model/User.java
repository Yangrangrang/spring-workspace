package com.example.springsecurity2inf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Setter @Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    private String username;
    private String password;
    private String email;
    private String role;    // ROLE_ADMIN, ROLE_USER
    private LocalDateTime createDate;
}
