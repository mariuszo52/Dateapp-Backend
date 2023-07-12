package com.dateapp.dateapp.login;


import com.dateapp.dateapp.jwtToken.JwtTokenService;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRegisterDto;
import com.dateapp.dateapp.user.UserService;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
public class LoginController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserInfoService userInfoService;

    public LoginController(UserService userService, JwtTokenService jwtTokenService, DaoAuthenticationProvider daoAuthenticationProvider, UserInfoService userInfoService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.userInfoService = userInfoService;
    }
    @GetMapping("/userinfo")
    ResponseEntity<UserInfoDto> getUserInfo(@RequestParam Long userId){
        UserInfoDto userInfoDto = userInfoService.getUserInfoByUserId(userId).orElseThrow();
        return ResponseEntity.ok().body(userInfoDto);
    }
    @PostMapping("/userinfo")
    ResponseEntity<String> addUserInfo(@RequestBody UserInfoDto userInfoDto){
        try{
            System.out.println(userInfoDto);
            userInfoService.addUserInfo(userInfoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            System.out.println(userRegisterDto);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userRegisterDto.getEmail(), userRegisterDto.getPassword());
            String username = authentication.getName();
            long id = userService.findUserByEmail(username).orElseThrow().getId();
            daoAuthenticationProvider.authenticate(authentication);
            String jwtToken = jwtTokenService.generateJwtToken(userRegisterDto);
            Map<String, String> map = Map.of("userId", String.valueOf(id),"jwt", jwtToken);
            return ResponseEntity.ok(map);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        try{
            Long id = userService.registerUser(userRegisterDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(id.toString());
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

