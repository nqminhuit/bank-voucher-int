package com.nqminhuit.gateway.filters.configs;

import com.nqminhuit.gateway.filters.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthFilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterBean() {
        var authFilterBean = new FilterRegistrationBean<AuthFilter>();
        authFilterBean.setFilter(new AuthFilter());
        authFilterBean.addUrlPatterns("/voucher/*");
        return authFilterBean;
    }
}
