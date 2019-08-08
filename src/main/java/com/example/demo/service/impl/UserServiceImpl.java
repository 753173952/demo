package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.enity.UserEnity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEnity> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(UserEnity userEnity) {
        userMapper.insert(userEnity);
    }

    @Override
    @Transactional
    public List<UserEnity> selectUserList(Page<UserEnity> page) {
        userMapper.selectUser(page);
        return userMapper.selectUser(page);
    }

    @Override
    @Transactional
    public void selectUser() {
        UserEnity userEnity1 = userMapper.selectById(1L);
        userEnity1.setUserName("guiling");
        UserEnity userEnity2 = userMapper.selectById(1L);
        UserEnity userEnity3 = userMapper.selectById(1L);
        System.out.println(userEnity1);
        System.out.println(userEnity2);
        System.out.println(userEnity3);
    }

    @Override
    public boolean selectUserByNameAndPassword(UserEnity userEnity) {
        QueryWrapper<UserEnity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userEnity.getUserName());
        queryWrapper.eq("user_password", userEnity.getUserPassword());
        UserEnity user = this.baseMapper.selectOne(queryWrapper);
        if (user != null) {
            return true;
        }
        return false;
    }
}
