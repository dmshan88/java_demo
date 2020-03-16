package com.example.common.jwt.impl;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.common.jwt.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilImpl implements JwtUtil {
    
    private static final String JWT_PROPERTY_SECRET = "app.jwt.secret";
    
    private static final String JWT_SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
    
    private static final String CLAIM_KEY_USER_NAME = "username";
    
    private final Environment environment;

    public JwtUtilImpl(Environment environment) {
        this.environment = environment;
    }

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
        String secret = this.environment.getProperty(JWT_PROPERTY_SECRET, JWT_SECRET);
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }


}
