package com.example.service.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "topic1", consumerGroup = "consumer1-topic1")
public class RocketmqListener implements RocketMQListener<String>{
    public void onMessage(String message) {
        System.out.println("received message: {}" + message);
    }
}