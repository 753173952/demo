package com.example.demo.common.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * hiberate校验配置
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/9
 */
@Configuration
public class ValidatorConfig {

    /**
     * 默认hiberate校验为全部参数校验
     * 将其修改为快速失败模式(即只要有一个参数校验失败就立即返回)
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }

    /**
     * 对方法参数校验启用快速失败模式(实测 并没看到哪里起了作用 默认就是单参数校验)
     */
    /*@Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        //设置为快速失败模式
        methodValidationPostProcessor.setValidator(validator());

        return methodValidationPostProcessor;
    }*/
}
