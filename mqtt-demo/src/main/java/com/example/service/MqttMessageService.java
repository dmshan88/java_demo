package com.example.service;

public interface MqttMessageService {

	void process(String topic, String message);
}
