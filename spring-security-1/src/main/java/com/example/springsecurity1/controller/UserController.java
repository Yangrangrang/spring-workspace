package com.example.springsecurity1.controller;

import com.example.springsecurity1.dto.UserAccountDto;
import com.example.springsecurity1.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserAccountService userAccountService;

    @GetMapping("/form")
    public String user(UserAccountDto dto) {
        return "/users/form";
    }

    @PostMapping("/form")
    public String joinForm(UserAccountDto dto) {

        return "/users/form";
    }
}
