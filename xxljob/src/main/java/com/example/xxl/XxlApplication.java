package com.example.xxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class XxlApplication {
    public static void main(String[] args) {
        SpringApplication.run(XxlApplication.class, args);
    }
}
