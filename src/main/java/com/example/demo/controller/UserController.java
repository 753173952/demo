package com.example.demo.controller;

import com.example.demo.common.config.CommonResult;
import com.example.demo.common.shiro_jwt.JWTUtils;
import com.example.demo.enity.UserEnity;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/2
 */
@RestController
@RunWith(SpringRunner.class)
@SpringBootTest
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @Test
    @RequestMapping("/addUser")
    public void addUser() {

        List<UserEnity> userEnityList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            UserEnity userEnity = new UserEnity();
            userEnity.setEmail("17687910227@163.com");
            userEnity.setAge(23);
            userEnity.setUserName("魏超超");
            userEnityList.add(userEnity);
        }
        long startTime = System.currentTimeMillis();
        userService.saveBatch(userEnityList);
        long endTime = System.currentTimeMillis();
        System.out.println("-----------------------" + (endTime - startTime) + "-----------------------");
    }


    @RequestMapping("login")
    public Object userLogin(@RequestBody @Valid UserEnity userEnity) {
        UserEnity user = userService.selectUserByNameAndPassword(userEnity);
        if (user != null) {
            // 用户存在则返回JWTToken给前台
            return new CommonResult().setResult(JWTUtils.createUserToken(user, user.getUserPassword()));
        }

        return new CommonResult().setFailed();
    }


}
