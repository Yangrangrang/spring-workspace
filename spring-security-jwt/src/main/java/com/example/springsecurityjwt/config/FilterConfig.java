package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.filter.MyFilter1;
import com.example.springsecurityjwt.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 시큐리티 필터가 우선
 */
@Configuration
public class FilterConfig {

    @Bean
    FilterRegistrationBean<MyFilter1> filter1() {
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>(new MyFilter1());
        bean.addUrlPatterns("/*"); // 모든 요청에서
        bean.setOrder(1);   // 낮은 번호가 필처 중에서 가장 먼저 실행
        return bean;
    }

    @Bean
    FilterRegistrationBean<MyFilter2> filter2() {
        FilterRegistrationBean<MyFilter2> bean = new FilterRegistrationBean<>(new MyFilter2());
        bean.addUrlPatterns("/*"); // 모든 요청에서
        bean.setOrder(0);   // 낮은 번호가 필처 중에서 가장 먼저 실행
        return bean;
    }

}
