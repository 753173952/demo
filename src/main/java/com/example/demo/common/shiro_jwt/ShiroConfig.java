package com.example.demo.common.shiro_jwt;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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

    /**
     * 设置过滤规则
     * anon：所有url都能访问
     * authc：需要认证才能访问
     * user：配置记住我或认证通过可以访问
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置登录路径
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置登录成功路径
        shiroFilterFactoryBean.setSuccessUrl("/");
        //设置认证失败路径
        //shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthcation");
        //设置JWT过滤器
        Map<String, Filter> filterMap = new HashMap<>(1);
        filterMap.put("jwtFilter", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义Url规则 shiro根据顺序从上到下去匹配 匹配了就不再继续验证
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        //所有的请求通过我们自定义的JWTFilter
        filterRuleMap.put("/**", "jwtFilter");
        //登录注册路径放行
        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/user/register", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 定义安全管理器
     * 设置自定自定义myRelam
     */
    @Bean(name = "securityManager")
    public DefaultSecurityManager getDefaultSecurityManager(@Qualifier("myRelam") MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    /*********************************************开启Shiro注解功能*****************************************************/
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);

        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {

        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);

        return advisor;
    }
}
