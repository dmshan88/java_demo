package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.DemoService;
import com.example.task.DemoTask;

@RestController
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
    private TaskExecutor taskExecutor;
	
	@Autowired
    private DemoService demoService;
	
	@GetMapping("test")	
	public void test() {
		log.info("aa");
		demoService.doSomeing("aaa");
		taskExecutor.execute(new DemoTask(demoService, "aab"));
		taskExecutor.execute(new DemoTask(demoService, "bbcd"));
		log.info("cc");
	}
}
