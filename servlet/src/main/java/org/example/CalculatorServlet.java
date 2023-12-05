package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * implements Servlet하면 메소드를 전부 override해야하지만, GenericServlet은 추상클래스로 필요한 메소드만 사용 가능!
 */
@WebServlet("/calculate")
//public class CalculatorServlet implements Servlet {
public class CalculatorServlet extends GenericServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    private ServletConfig servletConfig;

    // 서블릿 인스턴스는 한번만 호출 되기 때문에 다음 사용자 요청이 들어왔을때 init메소드는 호출되지 않는다.
//    @Override
//    public void init(ServletConfig servletConfig) throws ServletException {
//        log.info("init");
//        this.servletConfig = servletConfig;
//    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("service");

        int operand1 = Integer.parseInt(req.getParameter("operand1"));
        String operator = req.getParameter("operator");
        int operand2 = Integer.parseInt(req.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        PrintWriter writer = res.getWriter();
        writer.println(result);
    }

//    @Override
//    public void destroy() {
//        // resource release
//    }
//
//    // 기타 메소드
//    @Override
//    public ServletConfig getServletConfig() {
//        return this.servletConfig;
//    }
//
//    @Override
//    public String getServletInfo() {
//        return null;
//    }

}
