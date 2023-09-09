package com.dateapp.dateapp.tokens;

import com.dateapp.dateapp.user.UserRegisterDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokensService {

    public String generateToken(UserRegisterDto userRegisterDto, long expirationMinutes) {
        long now = System.currentTimeMillis();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userRegisterDto.getId());
        claims.put("username", userRegisterDto.getEmail());
        claims.put("role", "ROLE_USER");
        claims.put("password", userRegisterDto.getPassword());
        return Jwts.builder()
                .setSubject(userRegisterDto.getEmail())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationMinutes * 60 * 1000))
                .compact();

    }
}