package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private User user;

	@Override
	public User doSomething() {
		return user;
		
	}

}
