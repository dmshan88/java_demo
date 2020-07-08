package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.pojo.User;

public class BaseController {

    protected User user = null;
    
    @ModelAttribute
    protected void common(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            user = new User();
            user.setId(1);
            user.setName(subject.getPrincipal().toString());
        }
    }
    
}
