package com.example.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("test")
    public String test(String content) {
        System.out.println(rocketMQTemplate);
        rocketMQTemplate.convertAndSend("topic1", content == null ? "ok" : content);
        return "ok";
    }
}
