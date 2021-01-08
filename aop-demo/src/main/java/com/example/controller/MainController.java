package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aop.AuthToken;
import com.example.util.Test;

@RestController
public class MainController {
    
    @Autowired
    private Test test;
    
    @GetMapping("hello")
    public String hello(Integer id, String name, Integer age) {
//        Test test = new Test();
        test.doSometing();
        System.out.println("hello方法执行：id==>" + id + "，name==>" + name + "，age==>" + age);
        return "hi~ 我不需要用户权限";
    }
    
    @AuthToken
    @GetMapping("user")
    public String user(Integer id, String name, Integer age) {
        System.out.println("user方法执行：id==>" + id + "，name==>" + name + "，age==>" + age);
        return "hi~ 我需要登陆后才可以访问";
    }
    
    @AuthToken(role_name = { "admin", "Administrator" })
    @GetMapping("admin")
    public String admin(Integer id, String name, Integer age) {
        System.out.println("admin方法执行：id==>" + id + "，name==>" + name + "，age==>" + age);
        return "hi~ 我需要管理员身份才可以访问";
    }

}
