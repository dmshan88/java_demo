package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.CustomResponse;
import com.example.common.ErrorCode;
import com.example.pojo.User;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


@RestController
public class MainController {
    

    @GetMapping(path = "/test1")
    public String test() {
        return "ok";
    }
    

//    @ApiImplicitParams({@ApiImplicitParam(name = "token", paramType = "query", value= "不需要")})
    @GetMapping(path = "/login")
    public CustomResponse<String> login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("user1") && password.equals("pwd1")) {
            return CustomResponse.ok("aa");
        }
        return CustomResponse.error(ErrorCode.AUTH_ERROR);
    }
    
    @GetMapping("/11")
    public CustomResponse<User> test1() {
        return null;
    }

}
