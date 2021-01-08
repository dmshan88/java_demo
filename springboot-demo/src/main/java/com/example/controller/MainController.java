package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.properties.UserProperties;

@RestController
public class MainController {

    private final UserProperties userProperties;
    
    public MainController(UserProperties userProperties) {
        this.userProperties = userProperties;
    }
    
    @GetMapping("/test")
    public String test() {
        System.out.println(userProperties.toString());
        return "ok";
    }
}
