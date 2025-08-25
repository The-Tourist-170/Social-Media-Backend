package com.tourist.toddle.Config;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtPro {
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String tokenGen(org.springframework.security.core.Authentication auth){
    
        String jwt = Jwts.builder()
                        .issuer("Tourist")
                        .issuedAt(new Date())
                        .expiration(new Date(System.currentTimeMillis() + 86400000))
                        .claim("email", auth.getName())
                        .signWith(key)
                        .compact();
        
        return jwt;
    }

    public static String getEmailFromJwt(String jwt){
        jwt = jwt.substring(7);
        Claims claims = Jwts
                            .parser()
                            .verifyWith(key)
                            .build()
                            .parseSignedClaims(jwt)
                            .getPayload();
        
        String email = String.valueOf(claims.get("email"));
        return email;
    }
}
