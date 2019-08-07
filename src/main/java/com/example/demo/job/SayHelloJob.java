package com.example.demo.job;

import org.quartz.*;

/**
 * @DisallowConcurrentExecution 不允许相同的任务并发执行
 * @PersistJobDataAfterExecution 告诉Quartz在成功执行了job类的execute方法后（没有发生任何异常），更新JobDetail中JobDataMap的数据，
 * 使得该job（即JobDetail）在下一次执行的时候，JobDataMap中是更新后的数据，而不是更新前的旧数据
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class SayHelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("---------------------我是定时任务------------------");
    }
}
