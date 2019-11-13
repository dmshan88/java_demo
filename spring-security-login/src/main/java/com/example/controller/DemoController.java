package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @ModelAttribute
    void common() {
        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principl instanceof UserDetails) {
            currentUser = ((UserDetails)principl).getUsername();
        }else {
            currentUser = principl.toString();
        }
        System.out.println(currentUser);
    }
    
    @GetMapping(path = "/test")
    String test() {
        System.out.println("test:");
        System.out.println(authenticationManager);
        System.out.println(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("user1", "pwd1")));
        return "test";
    }
    
    @GetMapping(path = "/user")
    String user() {
        System.out.println("user:");
       
        return "res";
    }
    @GetMapping(path = "/admin")
    String admin() {
        System.out.println("admin:");
        return "admin";
    }
}
