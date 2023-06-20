package com.dateapp.dateapp.jwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return "/register".equals(path) || "/login".equals(path) || path.equals("/userinfo");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
        }
        else{
            Jws<Claims> claimsJws = parseToken(authHeader);
            System.out.println(claimsJws);
            UsernamePasswordAuthenticationToken authenticationByToken = getAuthenticationByToken(claimsJws);
            System.out.println(authenticationByToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationByToken);
            filterChain.doFilter(request, response);
        }



    }


    private Jws<Claims> parseToken(String authHeader) {
        final String token = authHeader.substring(7);
        return Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(Jws<Claims> jws) {
        String username = jws.getBody().get("username", String.class);
        String userRole = jws.getBody().get("role", String.class);
        System.out.println(username);
        System.out.println(userRole);
        return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton
                (new SimpleGrantedAuthority(userRole)));
    }

}

