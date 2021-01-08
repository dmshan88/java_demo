package com.example.config;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.job.MyJob;

@Configuration
public class QuartzConfig {
    
//    private JobService jobService;
//    
//    public QuartzConfig(JobService jobService) {
//        this.jobService = jobService;
//    }

    @Bean
    public JobDetail MyJobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob1", "myJobGroup1")
        // JobDataMap可以给任务execute传递参数
                .usingJobData("abc", new Date().toLocaleString())
//                .usingJobData("jobService", this.jobService)
                .storeDurably().build();
        return jobDetail;
    }

    @Bean
    public Trigger myTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(MyJobDetail())
                .withIdentity("start_of_day", "start_of_day")
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                // 每天0点执行
                .build();
        return trigger;
    }
}
