package com.example.demo.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Druid连接池配置
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/5
 */
@Configuration
public class DruidConfig {

    /**
     * 注册一个stateVIewServlet
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //初始化参数
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");

        return servletRegistrationBean;
    }

    /**
     * 创建一个过滤器
     */
    @Bean
    public FilterRegistrationBean druidfilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //将druid内置的过滤器注册
        filterRegistrationBean.setFilter(new WebStatFilter());
        //过滤器参数
        Map<String, String> initParams = new HashMap<>(8);
        //忽略过滤的格式
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        //设置过滤器参数
        filterRegistrationBean.setInitParameters(initParams);
        //设置过滤器名称
        filterRegistrationBean.setName("druidFilter");
        //设置过滤器的过滤路径
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
