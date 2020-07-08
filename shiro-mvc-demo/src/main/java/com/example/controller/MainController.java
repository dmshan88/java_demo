package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController extends BaseController {

    @RequiresAuthentication
    @GetMapping("/index")
    public String index() {
        System.out.println(user);
        return "index";
    }
    
    @RequiresPermissions(value = "user")
    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @ResponseBody
    @GetMapping("/login")
    public String login(String username,String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        return "login ok";
    }
    
    @GetMapping("/loginPage")
    public String login() {
        return "login";
    }

}
