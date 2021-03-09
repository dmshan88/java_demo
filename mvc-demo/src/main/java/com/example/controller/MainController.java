package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;
import com.example.service.UserService;
//https://github.com/baichengzhou/SpringMVC-Mybatis-Shiro-redis-0.2

@Controller
public class MainController {

//	private final Logger log = Logger.getLogger(MainController.class);
	private final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private UserService userService;

	@GetMapping(value = "/hello")
	public String hello(Model model) {
		model.addAttribute("message", userService.doSomething().toString());
		log.info("aaa{}", "bb");
		return "hello";
	}
	
	@ResponseBody
	@GetMapping(value = "/user")
	public User user() {
		User user =  new User();
		user.setId(15);
		user.setName("aa啊");
		return user;
	}
}