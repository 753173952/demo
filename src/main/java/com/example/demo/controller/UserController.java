package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.enity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UserController {
    @Autowired
    private UserService userService;

    @Test
    @RequestMapping("/addUser")
    public void addUser() {

        List<User> userList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setEmail("17687910227@163.com");
            user.setAge(23);
            user.setName("魏超超");
            userList.add(user);
        }
        long startTime = System.currentTimeMillis();
        userService.saveBatch(userList);
        long endTime = System.currentTimeMillis();
        System.out.println("-----------------------" + (endTime - startTime) + "-----------------------");
    }

    @Test
    @RequestMapping("/selectUser")
    public void selectUser() {
        Page page = new Page();
        page.setSize(10);
        page.setCurrent(1);
        IPage iPage = userService.page(page);
        System.out.println(iPage);
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setId(1L);
        user.deleteById(user.getId());
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("chaochao");
        user.setEmail("17687910227@163.com");
        user.setVersion(1);
        userService.getBaseMapper().updateById(user);
    }

    @Test
    @RequestMapping("selectUserList")
    public void selectUserList() {
        Page page = new Page();
        page.setCurrent(1);
        page.setSize(10);
        System.out.println(userService.selectUserList(page));
    }

    @Test
    public void testSelectUser() {
        userService.selectUser();
    }

    @Test
    public void testLog(){
        System.out.println(log);
        System.out.println(log.getClass());

    }

}
