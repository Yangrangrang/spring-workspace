package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 어떤 경로로 요청이 들어오더라도 dispatchServlet이 실행됩니다.
 */
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private RequestMappingHandlerMapping rmhm;

    @Override
    public void init() throws ServletException {
        log.info("init");

        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatchServlet] service started.");

        try {
            System.out.println("req.getRequestURI() = " + req.getRequestURI());
            System.out.println("req.getMethod() = " + req.getMethod());

            Controller handler = rmhm.findHandler(new HandlerKey(RequestMethod.valueOf(req.getMethod()) ,req.getRequestURI()));
            System.out.println("handler = " + handler);

            // user/form 등록 버튼 눌러서 post 로 들어올 경우 /redirect:/users 로 forward 되어서 에러남
            String viewName = handler.handleRequest(req, resp);
            System.out.println("viewName = " + viewName);

            // 즉, forward도 되고 redirect도 되야 함
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
//            System.out.println("requestDispatcher = " + requestDispatcher);
//            requestDispatcher.forward(req, resp);



        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }

}
