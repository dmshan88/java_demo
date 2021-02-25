package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.model.User;

@Configuration
public class AppConfig {
	
	@Value("${user.id}")
	private Integer id;
	
	@Value("${user.name}")
	private String name;
	
	@Bean
	public User user() {
		User user = new User();
		user.setId(id);
		user.setName(name);
		return user;
	}

}
