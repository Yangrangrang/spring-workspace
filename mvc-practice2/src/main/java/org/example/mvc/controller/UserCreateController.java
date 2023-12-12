package org.example.mvc.controller;

import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // user 추가
        UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
        return "redirect:/users";   // /users라는 경로를 가진 쪽으로 다시 한번 요청해줘 라고 응답을 보냄 > 웹브라우저가 해당 결과값을 받아서 users라는 경로로 get요청을 보내게 됩니다.
                                    // get요청이 들어오면 디스패처서블릿이 받아서 get요청에 users라는 것이 해당하는 애가 있니 라고 보면 해당하는 컨트롤러가 있기 때문에 UserListController가 실행됩니다.

    }
}
