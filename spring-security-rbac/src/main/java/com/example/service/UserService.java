package com.example.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.pojo.User;

public interface UserService {
    
    User findByUsername(String username);
    
    User findOne(Integer id);
    
    UserDetails getUserDetail(User user);

}
