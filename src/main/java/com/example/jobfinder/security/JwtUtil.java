package com.example.jobfinder.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDetails userDetails, Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        claims.put("id", userId);
        return createToken(claims, userDetails.getUsername());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate token", e);
        }
    }

    public String extractEmail(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public Long extractId(String token) {
        try {
            if (token == null || !token.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token format");
            }
            String jwtToken = token.replace("Bearer ", "");
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody();
            return claims.get("id", Long.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to extract ID from token", e);
        }
    }

    public String extractRole(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("role", String.class);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }
}