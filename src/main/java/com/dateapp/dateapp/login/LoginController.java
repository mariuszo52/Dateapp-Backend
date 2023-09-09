package com.dateapp.dateapp.login;


import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import com.dateapp.dateapp.tokens.TokensService;
import com.dateapp.dateapp.user.UserRegisterDto;
import com.dateapp.dateapp.user.UserService;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.Arrays;

@RestController
@CrossOrigin
public class LoginController {
    private final UserService userService;
    private final TokensService tokensService;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserInfoService userInfoService;

    public LoginController(UserService userService, TokensService tokensService,
                           DaoAuthenticationProvider daoAuthenticationProvider, UserInfoService userInfoService) {
        this.userService = userService;
        this.tokensService = tokensService;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.userInfoService = userInfoService;
    }
    @GetMapping("/refresh-token")
    ResponseEntity<String> refreshToken(HttpServletRequest request){
        String refreshingToken = request.getHeader("Refresh-token");
        final String token = refreshingToken.substring(7);
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            Long id = claims.getBody().get("id", Long.class);
            String username = claims.getBody().get("username", String.class);
            String password = claims.getBody().get("password", String.class);
            UserRegisterDto userRegisterDto = new UserRegisterDto(id,username, password);

            String jwtToken = tokensService.generateToken(userRegisterDto, 20);
            return ResponseEntity.ok(jwtToken);
        }catch (JwtException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/matched-user-info")
    ResponseEntity<?> getMatchedUserInfo(@RequestParam Long userId, @RequestParam Long chatId) {
        try {
            userInfoService.checkUserAccessToChat(userId, chatId);
            UserRegisterDto user = userService.findUserById(userId);
            UserInfoDto userInfo = user.getUserInfo();
            return ResponseEntity.ok().body(userInfo);
        } catch (UnauthorizedResourceAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        try {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
            daoAuthenticationProvider.authenticate(authentication);
            UserRegisterDto loggedUser = userService.findUserByEmail(userLoginDto.getEmail());
            String jwtToken = tokensService.generateToken(loggedUser, 20);
            String refreshingToken = tokensService.generateToken(loggedUser, 30 * 24 * 60);
            String[] response = {jwtToken, refreshingToken};
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        try{
            userService.registerUser(userRegisterDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

