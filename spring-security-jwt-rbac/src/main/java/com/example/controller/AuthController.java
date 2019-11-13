package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AuthService;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping(path = "/login")
    String loginPost(String username,String password) throws AuthenticationException {
        return authService.login( username, password );
    }
}
