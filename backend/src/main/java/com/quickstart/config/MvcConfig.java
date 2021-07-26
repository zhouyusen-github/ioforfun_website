package com.quickstart.config;

import com.quickstart.interceptor.UserRightInterceptor;
import com.quickstart.interceptor.VerificationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public UserRightInterceptor userRightInterceptor(){
        return new UserRightInterceptor();
    }

    @Bean
    public VerificationInterceptor verificationInterceptor(){
        return new VerificationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // add interceptor
        registry.addInterceptor(userRightInterceptor()).addPathPatterns("/favorite/**");
        registry.addInterceptor(verificationInterceptor()).addPathPatterns("/user/login","/user/register");
    }
}