package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomResponse;
import com.example.common.ErrorCode;
import com.example.compoment.JwtUtil;

@RestController
public class MainController {

    @Autowired
    private JwtUtil jwtUtil;
    
    @GetMapping(path = "/test")
    public String test(@RequestParam String token) {
        System.out.println(SecurityUtils.getSubject().getPrincipal());
        return "ok";
    }
    
    @GetMapping(path = "/login")
    public CustomResponse<String> login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("aa") && password.equals("bb")) {
            return CustomResponse.ok(jwtUtil.createJwt("aa", 3600 * 1000L));
        }
        return CustomResponse.error(ErrorCode.AUTH_ERROR);
    }


}
