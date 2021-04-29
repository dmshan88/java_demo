package com.example;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RocketmqStarterTest {
    
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void test() {
        System.out.println("aa");
    }
    
    @Test
    public void send() {
        System.out.println(rocketMQTemplate);
//        rocketMQTemplate.convertAndSend("topic1", "hello aa");
    }


}
