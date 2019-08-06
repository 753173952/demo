package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.enity.UserEnity;

import java.util.List;

/**
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/1
 */
public interface UserMapper extends BaseMapper<UserEnity> {

    /**
     * 查找用户
     *
     * @param page 分页参数对象
     * @return
     */
    List<UserEnity> selectUser(Page<UserEnity> page);

}
