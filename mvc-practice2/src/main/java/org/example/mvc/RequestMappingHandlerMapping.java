package org.example.mvc;

import org.example.mvc.controller.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {
    private static final Logger log = LoggerFactory.getLogger(RequestMappingHandlerMapping.class);
    // 예를들어 , /users[keys]라는 경로로 들어왔을 때 UserController[value]를 실행해줘 (key,value 형태로 저장하기 위해서 저장 관리하는 클래스)
    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init() {
        log.info("RequestMappingHandlerMapping init");

        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey) {
        log.info("RequestMappingHandlerMapping findHandler");

        return mappings.get(handlerKey);
    }
}
