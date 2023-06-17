package com.dateapp.dateapp.jwtToken;

import com.dateapp.dateapp.user.UserRegisterDto;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenService {


    public String generateJwtToken(UserRegisterDto userRegisterDto) {
        long now = System.currentTimeMillis();
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userRegisterDto.getEmail());
        claims.put("role", "ROLE_USER");
        return Jwts.builder()
                .setSubject(userRegisterDto.getEmail())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 30 * 60 * 1000))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();
    }
}
