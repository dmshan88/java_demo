package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AuthService;

@RestController
public class TestController {
    @Autowired
    private AuthService authService;
    
    @ModelAttribute
    void common() {
        System.out.print("common:");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }
    
    
    @Secured({"ROLE_USER"})
    @GetMapping(path = "/test")
    String  test(String token) {
        return "test";
    }
    
    @PostMapping(path = "/login")
    String  loginPost(String username,String password) throws AuthenticationException {
        return authService.login( username, password );
    }
}
