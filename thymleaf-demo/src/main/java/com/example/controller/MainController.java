package com.example.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping()
    String index(Model model) {
//        System.out.println("index");
        model.addAttribute("aa", "aaa");
        List<String> list1 = Arrays.asList("a1", "a2");
        model.addAttribute("thEach", list1);
        return "index";
    }
    
    @GetMapping("/test")
    String test(String message) {
        System.out.println("test");
        return "test";
    }

    

}
