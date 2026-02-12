package com.stefan.survivorgamebackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "my-super-secret-key-my-super-secret-key-my-super-secret-key";
    private final long JWT_EXPIRATION_TIME = 1000 * 60 * 60;

    private Key getSignInKey(){
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey())
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    private Date extractExpiration(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
                .parseClaimsJws(token).getBody().getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }
}
