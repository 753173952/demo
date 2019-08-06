package com.example.demo.service.impl;

import com.example.demo.common.exception.CustomerException;
import com.example.demo.enity.TaskEnity;
import com.example.demo.service.QuartzTaskService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartzTaskServiceImpl implements QuartzTaskService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public Boolean addTask(TaskEnity taskEnity) {
        try {
            if (checkExists(taskEnity.getJobName(), taskEnity.getJobGroup())) {
                return false;
            }
            TriggerKey triggerKey = new TriggerKey(taskEnity.getJobName(), taskEnity.getJobGroup());
            JobKey jobKey = new JobKey(taskEnity.getJobName(), taskEnity.getJobGroup());

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskEnity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(taskEnity.getJobDescription()).withSchedule(scheduleBuilder).build();
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(taskEnity.getJobClassName());
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(taskEnity.getJobDescription()).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            throw new CustomerException("系统异常！");
        }

        return true;
    }

    @Override
    public Boolean resumeTask(TaskEnity taskEnity) {
        try {
            scheduler.resumeJob(new JobKey(taskEnity.getJobName(), taskEnity.getJobGroup()));
            return true;
        } catch (SchedulerException e) {
            throw new CustomerException("系统异常！");
        }
    }

    @Override
    public List<TaskEnity> findTaskList(TaskEnity taskEnity) {

        return null;
    }

    @Override
    public Boolean updateTask(TaskEnity taskEnity) {
        return null;
    }

    @Override
    public Boolean pauseTask(TaskEnity taskEnity) {
        return null;
    }

    @Override
    public Boolean deleteTask(TaskEnity taskEnity) {
        return null;
    }

    /**
     * 检验Task是否已经存在
     */
    private Boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);

        return scheduler.checkExists(jobKey);
    }
}
