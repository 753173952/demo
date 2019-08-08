package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.enity.UserEnity;

import java.util.List;

public interface UserService extends IService<UserEnity> {

    void add(UserEnity userEnity);

    List<UserEnity> selectUserList(Page<UserEnity> page);

    void selectUser();

    /**
     * 用具用户名和密码查找用户
     *
     * @param userEnity
     * @return
     */
    boolean selectUserByNameAndPassword(UserEnity userEnity);
}
