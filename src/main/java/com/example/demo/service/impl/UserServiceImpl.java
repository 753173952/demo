package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.enity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional
    public List<User> selectUserList(Page<User> page) {
        userMapper.selectUser(page);
        return userMapper.selectUser(page);
    }

    @Override
    @Transactional
    public void selectUser() {
        User user1 = userMapper.selectById(1L);
        user1.setName("guiling");
        User user2 = userMapper.selectById(1L);
        User user3 = userMapper.selectById(1L);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
    }
}
