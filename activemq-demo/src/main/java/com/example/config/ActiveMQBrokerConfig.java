package com.example.config;

import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQBrokerConfig {
	
	@Bean
	public BrokerService BrokerService() throws Exception {
		BrokerService service = new BrokerService();
		service.setPersistent(false);
		service.addConnector("tcp://localhost:61616");
		service.addConnector("mqtt://localhost:1883");
		return service;
	}
}
