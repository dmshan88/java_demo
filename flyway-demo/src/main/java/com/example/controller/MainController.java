package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDAO;
import com.example.model.User;

@RestController
public class MainController {

    @Autowired
    private UserDAO userDAO;
    
    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> hello() {
        return userDAO.findAll();
    }
    
}
