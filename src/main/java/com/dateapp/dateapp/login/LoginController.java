package com.dateapp.dateapp.login;


import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import com.dateapp.dateapp.jwtToken.JwtTokenService;
import com.dateapp.dateapp.user.UserRegisterDto;
import com.dateapp.dateapp.user.UserService;
import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import com.dateapp.dateapp.userInfo.UserInfoService;
import com.dateapp.dateapp.userInfo.location.LocationDto;
import com.dateapp.dateapp.userInfo.location.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.*;

import static com.dateapp.dateapp.userInfo.UserInfoMapper.map;

@RestController
@CrossOrigin
public class LoginController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserInfoService userInfoService;
    private final LocationService locationService;

    public LoginController(UserService userService, JwtTokenService jwtTokenService, DaoAuthenticationProvider daoAuthenticationProvider, UserInfoService userInfoService, LocationService locationService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.userInfoService = userInfoService;
        this.locationService = locationService;
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
    ResponseEntity<String> login(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userRegisterDto.getEmail(), userRegisterDto.getPassword());
            daoAuthenticationProvider.authenticate(authentication);
            UserRegisterDto loggedUser = userService.findUserByEmail(userRegisterDto.getEmail());
            String jwtToken = jwtTokenService.generateJwtToken(loggedUser);
           return ResponseEntity.status(HttpStatus.CREATED).body(jwtToken);
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        try{
            userService.registerUser(userRegisterDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

