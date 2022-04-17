package com.cqupt.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description: 指定拦截内容的配置类
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/cqupt/**")
                .excludePathPatterns("/cqupt")
                .excludePathPatterns("/cqupt/login")
                .excludePathPatterns("/cqupt/doLogin")
                .excludePathPatterns("/cqupt/signup")
                .excludePathPatterns("/cqupt/doSignup")
        ;
    }
}