package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.enity.User;

import java.util.List;

public interface UserService extends IService<User> {

    void add(User user);

    List<User> selectUserList(Page<User> page);
}
