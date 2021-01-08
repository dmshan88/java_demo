package com.example.service.impl;

import org.springframework.stereotype.Service;

import com.example.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Override
    public void doSomething() {
        System.out.println("JobService exec" + System.currentTimeMillis());
    }

}
