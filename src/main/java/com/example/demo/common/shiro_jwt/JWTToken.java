package com.example.demo.common.shiro_jwt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/9
 */
@AllArgsConstructor
@NoArgsConstructor
public class JWTToken implements AuthenticationToken {
    private String token;

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
