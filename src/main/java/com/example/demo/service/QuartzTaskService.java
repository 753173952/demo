package com.example.demo.service;

import com.example.demo.enity.TaskEnity;

import java.util.List;

/**
 * quartz任务接口类
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/6
 */
public interface QuartzTaskService {
    /**
     * 添加定时任务
     */
    Boolean addTask(TaskEnity taskEnity);

    /**
     * 恢复定时任务
     */
    Boolean resumeTask(TaskEnity taskEnity);

    /**
     * 查询定时任务
     */
    List<TaskEnity> findTaskList(TaskEnity taskEnity);

    /**
     * 修改定时任务
     */
    Boolean updateTask(TaskEnity taskEnity);

    /**
     * 停止定时任务
     */
    Boolean pauseTask(TaskEnity taskEnity);

    /**
     * 删除定时任务
     */
    Boolean deleteTask(TaskEnity taskEnity);
}
