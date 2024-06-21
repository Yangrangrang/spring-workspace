package com.example.springsecurity2inf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"","/"})
    public String index() {
        // 머스테치 기본폴더
        return "index";
    }
}
