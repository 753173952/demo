package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.enity.JobEnity;
import com.example.demo.mapper.JobMapper;
import com.example.demo.service.QuartzJobService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class QuartzJobServiceImpl extends ServiceImpl<JobMapper, JobEnity> implements QuartzJobService {

    @Autowired
    private Scheduler scheduler;

    /**
     * 激活所有定时任务
     */
    @PostConstruct
    public void initJob() {
        log.info("---------------------------------项目启动开始激活定时任务---------------------------");
        List<JobEnity> jobEnityList = this.baseMapper.selectList(null);
        if (!CollectionUtils.isEmpty(jobEnityList)) {
            for (JobEnity jobEnity : jobEnityList) {
                addJob(jobEnity);
                log.info("初始化定时任务：{}，定时任务名称为：{}", jobEnity.getJobClassName(), jobEnity.getJobName());
            }
        }
        log.info("---------------------------------项目启动定时任务激活结束---------------------------");
    }

    /**
     * 添加任务
     */
    @Override
    public Boolean addJob(JobEnity jobEnity) {
        try {
            if (checkExists(jobEnity.getJobName(), jobEnity.getJobGroupName())) {
                return false;
            }
            TriggerKey triggerKey = new TriggerKey(jobEnity.getJobName(), jobEnity.getJobGroupName());
            JobKey jobKey = new JobKey(jobEnity.getJobName(), jobEnity.getJobGroupName());

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobEnity.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(jobEnity.getJobDescription()).withSchedule(scheduleBuilder).build();
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(jobEnity.getJobClassName());
            // requestRecovery(true) 集群模式下运行失败会由其他节点重新执行任务
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobKey).withDescription(jobEnity.getJobDescription()).requestRecovery(true).build();
            scheduler.scheduleJob(jobDetail, cronTrigger);
            this.saveOrUpdate(jobEnity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }

    /**
     * 更新任务
     */
    @Override
    public Boolean updateJob(JobEnity jobEnity) {
        try {
            TriggerKey triggerKey = new TriggerKey(jobEnity.getJobName(), jobEnity.getJobGroupName());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobEnity.getCronExpression());
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey, cronTrigger);
            this.saveOrUpdate(jobEnity);
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
    public Boolean resumeJob(JobEnity jobEnity) {
        try {
            scheduler.resumeJob(new JobKey(jobEnity.getJobName(), jobEnity.getJobGroupName()));
            this.saveOrUpdate(jobEnity);
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
    public Boolean pauseJob(JobEnity jobEnity) {
        try {
            JobKey jobKey = new JobKey(jobEnity.getJobName(), jobEnity.getJobGroupName());
            scheduler.pauseJob(jobKey);
            this.saveOrUpdate(jobEnity);
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
    public Boolean deleteJob(JobEnity jobEnity) {
        try {
            JobKey jobKey = new JobKey(jobEnity.getJobName(), jobEnity.getJobGroupName());
            scheduler.deleteJob(jobKey);
            this.deleteJob(jobEnity);
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
