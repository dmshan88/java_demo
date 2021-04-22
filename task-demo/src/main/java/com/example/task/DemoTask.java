package com.example.task;

import com.example.service.DemoService;

public class DemoTask implements Runnable {
	
	private DemoService demoService;
	
	private String value;
	
	public DemoTask(DemoService demoService, String value) {
		this.demoService = demoService;
		this.value = value;
	}

	@Override
	public void run() {
		demoService.doSomeing(value);		
	}

}
