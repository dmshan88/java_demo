package com.example.common.controller.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.controller.UserAuthController;
import com.example.common.core.CustomResponse;
import com.example.common.core.ErrorCode;
import com.example.common.jwt.JwtUtil;
import com.example.common.service.UserAuthService;

@RestController
@ConditionalOnMissingBean(value = UserAuthController.class, ignored = UserAuthControllerImpl.class)
public class UserAuthControllerImpl implements UserAuthController {
    
    private JwtUtil jwtUtil;
    
    private UserAuthService userAuthService;
    
    public UserAuthControllerImpl(UserAuthService userAuthService, JwtUtil jwtUtil) {
        this.userAuthService = userAuthService;
        this.jwtUtil = jwtUtil;
    }
    
    @Override
    @GetMapping(path = "/login")
    public CustomResponse<String> login(@RequestParam String username, @RequestParam String password) {
        if (userAuthService.authUserAndPassword(username, password)) {
            return CustomResponse.ok(jwtUtil.createJwt(username, 3600 * 1000L));
        }
        return CustomResponse.error(ErrorCode.AUTH_ERROR);
    }
}
