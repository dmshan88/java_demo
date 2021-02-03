package com.example.task;

import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQServer implements ApplicationRunner {
	
	@Autowired
	private BrokerService brokerService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		brokerService.start();
	}

}
