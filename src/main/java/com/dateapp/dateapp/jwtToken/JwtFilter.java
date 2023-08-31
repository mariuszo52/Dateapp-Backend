package com.dateapp.dateapp.jwtToken;

import com.dateapp.dateapp.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return notFilteredPaths(request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("authorization");
        boolean isOptionsMethod = request.getMethod().equals(HttpMethod.OPTIONS.name());
        if (isOptionsMethod) {
            filterChain.doFilter(request, response);
        } else if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        } else {
            Jws<Claims> claimsJws = parseToken(authHeader);
            UsernamePasswordAuthenticationToken authenticationByToken = getAuthenticationByToken(claimsJws);
            SecurityContextHolder.getContext().setAuthentication(authenticationByToken);
            filterChain.doFilter(request, response);
        }


    }


    private Jws<Claims> parseToken(String authHeader) {
        final String token = authHeader.substring(7);
        return Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(Jws<Claims> jws) {
        Long id = jws.getBody().get("id", Long.class);
        String username = jws.getBody().get("username", String.class);
        String userRole = jws.getBody().get("role", String.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(userRole)));
        User user = new User(id, username);
        authenticationToken.setDetails(user);
        return authenticationToken;
    }

    private boolean notFilteredPaths(HttpServletRequest request) {
        String path = request.getRequestURI();
        System.out.println(path);
        return path.equals("/register") || path.equals("/login")
                || path.equals("/userinfo") || path.startsWith("/chat")
                || path.startsWith("/h2-console") || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui");
    }

}

