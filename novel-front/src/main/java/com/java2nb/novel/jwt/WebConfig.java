package com.java2nb.novel.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shaotian
 * @create 2020-11-29 10:49
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 自定义视图转发器
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("index");
    }

    /**
     * 加载自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求  除了转发到首页的请求
        registry.addInterceptor(new Interceptor()).addPathPatterns("/**")
                .excludePathPatterns("/user/**", "/config/**", "/**/getVerify", "/layuimini/**"
                        , "/css/**", "/images/**", "/layui/**", "/javascript/**");
    }

}
