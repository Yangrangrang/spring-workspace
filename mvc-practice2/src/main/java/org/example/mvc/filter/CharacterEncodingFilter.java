package org.example.mvc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 한글 깨짐 현상으로 Filter적용
 * @WebFilter 언제 적용? ("/*") 모든 경우 적용
 *
 * 스프링 시큐리티 > 필터 개념 servlet에서 사용하는 개념
 */
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    public static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(DEFAULT_ENCODING);
        response.setCharacterEncoding(DEFAULT_ENCODING);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
