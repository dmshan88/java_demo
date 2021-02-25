package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.UserService;
//https://github.com/baichengzhou/SpringMVC-Mybatis-Shiro-redis-0.2

@Controller  
public class MainController {  
	
	@Autowired
	private UserService userService;
	
    @GetMapping(value="/hello")  
    public String hello(Model model) {  
        model.addAttribute("message", userService.doSomething().toString());  
        return "hello";  
    }  
}