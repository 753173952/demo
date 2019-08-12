package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

@JobHandler(value = "sayHelloJob")
@Component
public class SayHelloWordHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println("sayHelloWord：" + i);

        }
        return SUCCESS;
    }
}
