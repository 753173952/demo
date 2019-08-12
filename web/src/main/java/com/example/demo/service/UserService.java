package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.enity.UserEnity;

public interface UserService extends IService<UserEnity> {

    /**
     * 用具用户名和密码查找用户
     *
     * @param userEnity
     * @return
     */
    UserEnity selectUserByNameAndPassword(UserEnity userEnity);

    /**
     *
     */
    UserEnity selectUserByUserName(String userName);
}
