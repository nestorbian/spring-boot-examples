package com.nestor.hibernatevalidation.config;

import com.nestor.hibernatevalidation.interceptor.MyHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2022/10/17
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    // @Override
    // public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    //     resolvers.add(new CustomArgArgumentResolver());
    // }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor());
    }
}
