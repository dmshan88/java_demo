package com.example.service.impl;

import org.springframework.stereotype.Service;

import com.example.service.MqttMessageService;

@Service
public class MqttMessageServiceImpl implements MqttMessageService {

    @Override
    public void process(String topic, String message) {
        System.out.println("process");
        System.out.println("messageArrived");
        System.out.println(topic);
        System.out.println(message);

    }

}
