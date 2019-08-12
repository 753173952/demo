package com.example.demo.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
/**
* @Author: WeiChaoChao 
* @Email: 17687910227@163.com
* @Date: 2019/8/7 
*/ 
public class SayHelloJobListener implements JobListener {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {

    }
}
