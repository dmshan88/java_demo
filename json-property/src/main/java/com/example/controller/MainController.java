package com.example.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.UserConfig;
import com.example.model.User;
import com.example.utils.JsonUtils;


@RestController
public class MainController {
	
	@Autowired
	private User user;

	@GetMapping("/read")
	public User read() throws IOException {
		return user;
	}
	
	@GetMapping("/write")
	public void write(User object) throws IOException {
		this.user = object;
		JsonUtils.saveJsonToFile(object, new File(UserConfig.path));		
	}
}
