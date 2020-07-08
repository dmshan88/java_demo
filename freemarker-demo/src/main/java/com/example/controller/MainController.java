package com.example.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("aa", "aaa");
        return "index";
    }
    
    @ResponseBody
    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test() {
        return "{'aa':'bb'}";
    }

    @GetMapping(path = "/ext")
    public String ext() {
        throw new RuntimeException("aa");
    }

}
