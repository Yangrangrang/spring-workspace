package com.fastcampus.projectboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        Logger log = LoggerFactory.getLogger(MainController.class);
        log.info("root");

        return "forward:/articles";
    }
}
