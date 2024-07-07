package com.example.springsecurityjwt.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class MyFilter3 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터3");
        chain.doFilter(request,response);   // 프로그램이 끝나지 말고 계속 진행하라고 하면 꼭 다시 chain 에 넘겨줘야함.
    }
}
