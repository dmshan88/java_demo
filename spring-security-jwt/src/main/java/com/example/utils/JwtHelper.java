package com.example.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Log4j
public class JwtHelper {
    
    private static final String JWT_SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";
    private static final long TOKEN_EXPIRED_TIME = 12 * 3600 * 1000;
    
    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
    public static String createJWT(Map<String, Object> claims) {
        long timestamp = new Date().getTime();
        return Jwts.builder()
            .setClaims(claims)
            .signWith(getKeyInstance(), SignatureAlgorithm.HS256)
            .setExpiration(new Date(timestamp + TOKEN_EXPIRED_TIME))
            .compact();
    }

    //解析Token，同时也能验证Token，当验证失败返回null
    public static Map<String, Object> verifyJwt(String token) {
        try {
            Map<String, Object> jwtClaims = Jwts.parser()
                .setSigningKey(getKeyInstance())
                .parseClaimsJws(token)
                .getBody();
            return jwtClaims;
        } catch (Exception e) {
            log.error("json web token verify failed");
            return null;
        }
    }
}