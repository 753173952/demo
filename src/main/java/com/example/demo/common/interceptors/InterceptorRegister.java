package com.example.demo.common.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器注册类
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/8
 */
@Component
public class InterceptorRegister extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //注册用户token校验拦截器
        registry.addInterceptor(new UserTokenInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login", "/user/register");
    }
}
