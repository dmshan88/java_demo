package com.example.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.example.service.JobService;

@Scope("prototype")
@Component
public class MyJob extends QuartzJobBean {
    
    private JobService jobService;
    
    private String abc;
    
    public MyJob(JobService jobService) {
        this.jobService = jobService;
    }

//    public String getAbc() {
//        return abc;
//    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

//    public JobService getJobService() {
//        return jobService;
//    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        jobService.doSomething();
        System.out.println("myjob exec" + System.currentTimeMillis());
    }

}
