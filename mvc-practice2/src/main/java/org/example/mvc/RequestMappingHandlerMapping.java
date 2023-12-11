package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    // 예를들어 , /users[keys]라는 경로로 들어왔을 때 UserController[value]를 실행해줘 (key,value 형태로 저장하기 위해서 저장 관리하는 클래스)
    private Map<String, Controller> mappings = new HashMap<>();

    void init() {
        mappings.put("/", new HomeController());
    }

    public Controller findHandler(String uriPath) {
        return mappings.get(uriPath);
    }
}
