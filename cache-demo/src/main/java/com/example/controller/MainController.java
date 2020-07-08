package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.BookDAO;

@RestController
public class MainController {

    @Autowired
    private BookDAO bookDao;



    @GetMapping(path = "/test")
    String test(String name) {
        if (name == null) {
            name = "aaa";
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(bookDao.getData(name));
        System.out.println(System.currentTimeMillis());
        return "ok";
    }
    
    @GetMapping(path = "/test1")
    String test1(String name) {
        if (name == null) {
            name = "bbb";
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(bookDao.getData1(name));
        System.out.println(System.currentTimeMillis());
        return "ok";
    }
    
    @GetMapping(path = "/test2")
    String test2(String name) {
        if (name == null) {
            name = "bbb";
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(bookDao.getData2(name));
        System.out.println(System.currentTimeMillis());
        return "ok";
    }
    
}
