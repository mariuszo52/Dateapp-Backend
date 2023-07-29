package com.dateapp.dateapp.config.security;


import com.dateapp.dateapp.jwtToken.JwtFilter;
import com.dateapp.dateapp.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable);
        security.headers(configurer -> configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        security.formLogin(AbstractHttpConfigurer::disable);
        security.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        security.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return security.authorizeHttpRequests(request -> request
                        .anyRequest().permitAll())
                .build();
        /*
        requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/chat/**").permitAll()
                        .requestMatchers("/userinfo/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/login").permitAll()
                        .anyRequest().hasRole("USER"))
         */

    }
    @Bean
    PasswordEncoder bcryptEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(bcryptEncoder());
        return authenticationProvider;
    }


}