package com.example.task;

import java.util.Locale;

import org.springframework.jms.core.JmsTemplate;

import com.example.model.User;
import com.github.javafaker.Faker;

public class JmsSendTask implements Runnable {
    
    private JmsTemplate jmsTemplate;
    
    private String topic;
    
    private static Faker faker = new Faker(Locale.CHINA);
    
    public JmsSendTask(JmsTemplate jmsTemplate, String topic) {
        this.topic = topic;
        this.jmsTemplate = jmsTemplate;
        
    }

    @Override
    public void run() {
        User user = new User(Long.valueOf(faker.number().randomNumber()).intValue(), faker.name().fullName());
        jmsTemplate.convertAndSend(topic, user);
        
    }

}
