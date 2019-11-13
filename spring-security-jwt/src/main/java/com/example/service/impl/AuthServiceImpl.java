package com.example.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.service.AuthService;
import com.example.utils.CustomToken;
import com.example.utils.JwtHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        CustomToken token = new CustomToken();
        token.setUsername(username);
        Map<String, Object> claims = new ObjectMapper().convertValue(token, new TypeReference<Map<String, Object>>(){});
        return JwtHelper.createJWT(claims);
    }

}
