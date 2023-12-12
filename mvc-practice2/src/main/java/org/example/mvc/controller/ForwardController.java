package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 해당 경로로 이동
 */
public class ForwardController implements Controller{
    private final String forwardPath;

    public ForwardController(String forwardPath) {
        this.forwardPath = forwardPath;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return forwardPath;
    }
}
