package com.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	
	private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Override
	public void doSomeing(String value) {
		log.info("demo Service do" + value);
		
	}

}
