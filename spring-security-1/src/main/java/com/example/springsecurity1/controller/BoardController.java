package com.example.springsecurity1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/from")
    public String boardInsert() {
        log.info("[BoardController] - insert");
        return "/insert";
    }
}
