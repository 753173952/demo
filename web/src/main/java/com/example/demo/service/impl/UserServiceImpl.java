package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.enity.UserEnity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEnity> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEnity selectUserByNameAndPassword(UserEnity userEnity) {
        QueryWrapper<UserEnity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userEnity.getUserName());
        queryWrapper.eq("user_password", userEnity.getUserPassword());

        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public UserEnity selectUserByUserName(String userName) {
        QueryWrapper<UserEnity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);

        return this.baseMapper.selectOne(queryWrapper);
    }
}
