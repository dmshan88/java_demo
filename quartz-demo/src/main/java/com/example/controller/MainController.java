package com.example.controller;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @Autowired
    private Scheduler scheduler;

    @GetMapping("test")
    public String test() throws SchedulerException {
        System.out.println(scheduler.getSchedulerName());
        System.out.println(scheduler.getCurrentlyExecutingJobs().size());
        System.out.println(scheduler.getJobDetail(new JobKey("myJob1","myJobGroup1")));
        return "ok";
    }
}
