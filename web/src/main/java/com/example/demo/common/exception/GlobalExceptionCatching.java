package com.example.demo.common.exception;

import com.example.demo.common.config.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

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
        log.error("bindExceptionErrorHandler info:{}", ex);
        BindingResult bindingResult = ex.getBindingResult();

        return new CommonResult().setFailed().setMsg(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }


    @ExceptionHandler(value = ConstraintViolationException.class)
    public Object handleBindGetException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException info:{}", ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        String errorMsg = constraintViolations.iterator().next().getMessage();

        return new CommonResult().setFailed().setMsg(errorMsg);
    }
}
