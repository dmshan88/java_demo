package com.example.compoment.impl;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.compoment.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilImpl implements JwtUtil {
    
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;
    
    private final String CLAIM_KEY_USER_NAME = "username";

    @Override
    public String createJwt(String username, Long expiredTime) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expiredTime);
        return Jwts.builder()
            .claim(CLAIM_KEY_USER_NAME, username)
            .signWith(getKeyInstance())
            .setIssuedAt(now)
            .setExpiration(expireDate)
            .setId(UUID.randomUUID().toString())
            .compact();
    }

    @Override
    public String parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(getKeyInstance())
                .parseClaimsJws(token)
                .getBody()
                .get(CLAIM_KEY_USER_NAME, String.class);

    }
    
    private Key getKeyInstance() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }


}
