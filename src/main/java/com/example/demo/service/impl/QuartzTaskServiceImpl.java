package com.example.demo.service.impl;

import com.example.demo.enity.TaskEnity;
import com.example.demo.service.QuartzTaskService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuartzTaskServiceImpl implements QuartzTaskService {

    @Autowired
    private Scheduler scheduler;

    @Override
    public List<TaskEnity> findTaskList(TaskEnity taskEnity) {

        return null;
    }

    /**
     * 添加任务
     */
    @Override
    public Boolean addTask(TaskEnity taskEnity) {
        try {
            if (checkExists(taskEnity.getJobName(), taskEnity.getJobGroupName())) {
                return false;
            }
            TriggerKey triggerKey = new TriggerKey(taskEnity.getJobName(), taskEnity.getJobGroupName());
            JobKey jobKey = new JobKey(taskEnity.getJobName(), taskEnity.getJobGroupName());

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskEnity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(taskEnity.getJobDescription()).withSchedule(scheduleBuilder).build();
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(taskEnity.getJobClassName());
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(taskEnity.getJobDescription()).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }

    /**
     * 更新任务
     */
    @Override
    public Boolean updateTask(TaskEnity taskEnity) {
        try {
            TriggerKey triggerKey = new TriggerKey(taskEnity.getJobName(), taskEnity.getJobGroupName());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(taskEnity.getCronExpression());
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, cronTrigger);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 恢复任务
     */
    @Override
    public Boolean resumeTask(TaskEnity taskEnity) {
        try {
            scheduler.resumeJob(new JobKey(taskEnity.getJobName(), taskEnity.getJobGroupName()));
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }


    /**
     * 暂停任务
     */
    @Override
    public Boolean pauseTask(TaskEnity taskEnity) {
        try {
            JobKey jobKey = new JobKey(taskEnity.getJobName(), taskEnity.getJobGroupName());
            scheduler.pauseJob(jobKey);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 删除任务
     */
    @Override
    public Boolean deleteTask(TaskEnity taskEnity) {
        try {
            JobKey jobKey = new JobKey(taskEnity.getJobName(), taskEnity.getJobGroupName());
            scheduler.deleteJob(jobKey);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }

    /**
     * 检验Task是否已经存在
     */
    private Boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, jobGroup);

        return scheduler.checkExists(jobKey);
    }
}
