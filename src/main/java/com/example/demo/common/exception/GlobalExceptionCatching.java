package com.example.demo.common.exception;

import com.example.demo.common.config.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionCatching {

    @ExceptionHandler(Exception.class)
    public Object catchingException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage(), e);

        return new CommonResult().setFailed().setMsg(e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public Object bindExceptionErrorHandler(BindException ex) throws Exception {
        log.error("bindExceptionErrorHandler info:{}", ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (bindingResult.hasFieldErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append(fieldError.getDefaultMessage());
                stringBuilder.append("！");
            }
        }
        return new CommonResult().setFailed().setResult(stringBuilder.toString());
    }
}
