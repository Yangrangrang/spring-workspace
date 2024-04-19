package com.example.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MdcController {
    /*
    멀티쓰레드 환경에서 로그를 남길 때 사용하는 개념
    MDC를 사용할 때는 put과 clear를 같이 사용해야한다.
     */

    @GetMapping("/mdc")
    public String mdc() {
        MDC.put("jdb", "dev");
        log.trace("log --> TRACE");
        log.debug("log --> DEBUG");
        log.info("log --> INFO");
        log.warn("log --> WARN");
        log.error("log --> ERROR");

        MDC.clear();
        return "mdc";
    }
}
