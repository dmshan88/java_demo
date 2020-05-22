package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping()
    String index(Model model) {
        System.out.println("index");
        model.addAttribute("aa", "aaa");
        return "index";
    }
    
    @GetMapping("/test")
    String test(String message) {
        System.out.println("test");
        return "sub/bb";
    }

    

}
