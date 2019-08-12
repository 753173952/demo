package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.enity.JobEnity;

/**
 * quartz任务接口类
 *
 * @Author: WeiChaoChao
 * @Email: 17687910227@163.com
 * @Date: 2019/8/6
 */
public interface QuartzJobService extends IService<JobEnity> {


    /**
     * 添加定时任务
     */
    Boolean addJob(JobEnity jobEnity);

    /**
     * 恢复定时任务
     */
    Boolean resumeJob(JobEnity jobEnity);

    /**
     * 修改定时任务
     */
    Boolean updateJob(JobEnity jobEnity);

    /**
     * 停止定时任务
     */
    Boolean pauseJob(JobEnity jobEnity);

    /**
     * 删除定时任务
     */
    Boolean deleteJob(JobEnity jobEnity);
}
