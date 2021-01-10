package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.properties.UserProperties;

@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    
    private final UserProperties userProperties;
    
    public MainController(UserProperties userProperties) {
        this.userProperties = userProperties;
    }
    
    @GetMapping("/test")
    public String test() {
        logger.info(userProperties.toString());
        return "ok";
    }
}
