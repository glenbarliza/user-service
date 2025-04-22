package com.microservices.user.config;

import com.microservices.user.security.InternalTokenAuthFilter;
import com.microservices.user.security.JwtAuthFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

// Registers the JwtAuthFilter to be applied on secured routes
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<OncePerRequestFilter> jwtFilter(JwtAuthFilter jwtAuthFilter) {
        FilterRegistrationBean<OncePerRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtAuthFilter);

        // Apply filter only to desired paths
        registrationBean.addUrlPatterns("/users/*", "/me");

        registrationBean.setOrder(1); // Priority
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<InternalTokenAuthFilter> internalTokenFilter(InternalTokenAuthFilter filter) {
        FilterRegistrationBean<InternalTokenAuthFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(filter);
        bean.addUrlPatterns("/users/internal/*");
        bean.setOrder(0); // Mayor prioridad que el filtro JWT si lo hubiera
        return bean;
    }
}
