package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomException;

@RestController
public class MainController {
    
    @GetMapping("/test")
    String test() throws CustomException {
        return "hello";
    }
    

}
