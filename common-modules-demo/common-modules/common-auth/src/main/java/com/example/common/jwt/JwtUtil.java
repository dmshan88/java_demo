package com.example.common.jwt;

public interface JwtUtil {
    
    public String createJwt(String username, Long expiredTime);
    
    public String parseJwt(String token);

}
