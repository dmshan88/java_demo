package com.example.controller;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.User;

@Validated
@RestController
public class MainController {

    @GetMapping("/test")
    public String test(@Size(max = 10) String name) {
        System.out.println(name);
        return "ok";
    }

    @GetMapping("/test1")
    public String test1(@Valid User user) {
        return "ok";
    }
}
