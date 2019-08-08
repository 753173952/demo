package com.example.demo.common.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.common.tools.JWTUtils;
import com.example.demo.enity.UserEnity;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户Token认证
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/8
 */
@Component
@Slf4j
public class UserTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("无token信息，请重新登录！");
        }

        //验证token是否有效
        DecodedJWT decodedJWT = JWTUtils.verifyUserToken(token);
        //验证token是否过期
        if (decodedJWT.getExpiresAt().getTime() < System.currentTimeMillis()) {
            throw new RuntimeException("token信息过期，请重新登录！");
        }

        //验证用户信息是否存在
        String userId = decodedJWT.getClaim("id").asString();
        if (StringUtils.isNotBlank(userId)) {
            throw new RuntimeException("用户信息不存在，请重新登录！");
        }
        UserEnity user = userService.getById(userId);
        if (user == null) {
            throw new RuntimeException("用户信息不存在，请重新登录！");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
