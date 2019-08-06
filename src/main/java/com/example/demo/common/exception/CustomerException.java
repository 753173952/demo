package com.example.demo.common.exception;

import lombok.Data;

/**
 * 自定义异常
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/6
 */
@Data
public class CustomerException extends RuntimeException {
    private Integer code = 500;
    private String message = "系统错误！";


    public CustomerException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomerException(String message) {
        super(message);
        this.message = message;
    }

}
