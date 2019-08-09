package com.example.demo.common.shiro_jwt;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/9
 */
@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置JWT过滤器
        Map<String, Filter> filterMap = new HashMap<>(1);
        filterMap.put("jwtFilter", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义Url规则
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        //所有的请求通过我们自定义的JWTFilter
        filterRuleMap.put("/**", "jwtFilter");
        //登录注册路径放行
        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/user/register", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultSecurityManager getDefaultSecurityManager(@Qualifier("myRelam") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean("myRelam")
    public MyRealm getMyRealm() {
        return new MyRealm();
    }
}
