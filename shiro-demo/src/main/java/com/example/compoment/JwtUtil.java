package com.example.compoment;

public interface JwtUtil {
    
    public String createJwt(String username, Long expiredTime);
    
    public String parseJwt(String token);

}
