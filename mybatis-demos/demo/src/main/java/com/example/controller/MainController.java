package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.entity.User;
import com.example.model.enums.UserTypeEnum;

@RestController
public class MainController {
    
    @GetMapping("test")
    public User test() {
        User user = new User();
        user.setId(1);
        user.setName("aa");
        user.setType(UserTypeEnum.Student);
        return user;
    }
    
    @GetMapping("test1")
    public void test1(User user) {
        System.out.println(user);
    }

}
