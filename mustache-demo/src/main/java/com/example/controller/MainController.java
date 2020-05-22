package com.example.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping()
    String index(Model model) {

        model.addAttribute("aa", "aaa");
        model.addAttribute("bb", Arrays.asList("b1", "b2"));
        System.out.println("index");
        return "index";
    }
    
    @GetMapping("/test")
    String test(String message) {
        System.out.println("test");
        return "test";
    }

    

}
