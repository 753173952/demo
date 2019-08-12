package com.example.demo.common.shiro_jwt;

import com.example.demo.enity.UserEnity;
import com.example.demo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义MyRealm
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/9
 */
@Component("myRelam")
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 这个方法必须重写  不然那shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从JWTToken中解析拿到userName
        String userName = JWTUtils.getUserName(principalCollection.toString());
        UserEnity userEnity = userService.selectUserByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //TODO 找到了这个用户然后获得这个用户的权限 自定义添加权限实现自己的逻辑规则

        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        //从JWTToken中解析获得用户名  然后与数据库中的数据进行对比
        String userName = JWTUtils.getUserName(token);
        if (StringUtils.isBlank(userName)) {
            throw new AuthenticationException("token无效！");
        }
        UserEnity userEnity = userService.selectUserByUserName(userName);
        if (userEnity == null) {
            throw new AuthenticationException("用户不存在！");
        }
        //校验JWTToken中的信息
        JWTUtils.verifyUserToken(token, userEnity.getUserPassword());

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
