package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.DemoDataDAO;

@RestController
public class MainController {
    
    @Autowired
    private DemoDataDAO dao;
    
    @GetMapping(path = "/test")
    void test() {
        System.out.println(dao.calcSum());
    }
}
