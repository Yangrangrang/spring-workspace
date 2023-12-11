package org.example.mvc;

import org.example.mvc.controller.Controller;
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
        rmhm = new RequestMappingHandlerMapping();
        rmhm.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatchServlet] service started.");

        Controller handler = rmhm.findHandler(req.getRequestURI());

        try {
            String viewName = handler.handleRequest(req, resp);
            System.out.println("viewName = " + viewName);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
            requestDispatcher.forward(req, resp);

        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);

        }
    }

}
