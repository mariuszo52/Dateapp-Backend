package com.dateapp.dateapp.login;


import com.dateapp.dateapp.jwtToken.JwtTokenService;
import com.dateapp.dateapp.user.UserRegisterDto;
import com.dateapp.dateapp.user.UserService;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoService;
import com.dateapp.dateapp.userInfo.location.LocationDto;
import com.dateapp.dateapp.userInfo.location.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.dateapp.dateapp.config.security.EndpointAccessCheckService.checkDataAccessPermission;

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
    @GetMapping("/user-info")
    ResponseEntity<UserInfoDto> getUserInfo(@RequestParam Long userId) {
        try {
            checkDataAccessPermission(userId);
            UserInfoDto userInfoDto = userInfoService.getUserInfoByUserId(userId).orElseThrow();
            return ResponseEntity.ok().body(userInfoDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
        @GetMapping("/matched-user-info")
        ResponseEntity<UserInfoDto> getMatchedUserInfo(@RequestParam Long userId){
            try {
                //przekazac match i sprawdzic czy user nalezy do maczu
                UserInfoDto userInfoDto = userInfoService.getUserInfoByUserId(userId).orElseThrow();
                return ResponseEntity.ok().body(userInfoDto);
            }catch (RuntimeException e){
                return ResponseEntity.badRequest().build();
            }

    }
    @PostMapping("/userinfo")
    ResponseEntity<String> addUserInfo(@RequestBody UserInfoDto userInfoDto){
        try{
            LocationDto locationDto = locationService.save(userInfoDto.getLocationDto()).orElseThrow();
            userInfoDto.setLocationDto(locationDto);
            userInfoService.addUserInfo(userInfoDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userRegisterDto.getEmail(), userRegisterDto.getPassword());
            String username = authentication.getName();
            Long id = userService.findUserByEmail(username).orElseThrow().getId();
            userRegisterDto.setId(id);
            daoAuthenticationProvider.authenticate(authentication);
            String jwtToken = jwtTokenService.generateJwtToken(userRegisterDto);
            Map<String, String> map = Map.of("userId", String.valueOf(id),"jwt", jwtToken);
            return ResponseEntity.ok(map);
        } catch (RuntimeException  e) {
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

