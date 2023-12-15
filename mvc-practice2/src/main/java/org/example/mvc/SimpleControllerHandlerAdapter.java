package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 전달된 핸들러가 컨트롤로 인터페이스를 구현한 구현체라면 지원을 해주겠다
 * 핸들을 호출 하면 탈캐스팅을 해줌
 * ((Controller) handler).handleRequest(request, response); >> ex) HomeController, UserListController, UserCreateController.....등
 */
public class SimpleControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof Controller);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((Controller) handler).handleRequest(request, response);
        return new ModelAndView(viewName);
    }
}
