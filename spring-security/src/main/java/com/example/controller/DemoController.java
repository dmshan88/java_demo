package com.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @ModelAttribute
    void common() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }
    @GetMapping(path = "/test")
    String test() {
        return "test";
    }
    @GetMapping(path = "/user")
    String user() {
        return "user";
    }
    @GetMapping(path = "/admin")
    String admin() {
        return "admin";
    }
    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/method")
    String method() {
        return "method";
    }
}
