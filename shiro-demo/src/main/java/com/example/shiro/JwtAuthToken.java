package com.example.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtAuthToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private final String token;

    public JwtAuthToken(String token) {
        this.token = token;
    }
    
    public String getToken() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
