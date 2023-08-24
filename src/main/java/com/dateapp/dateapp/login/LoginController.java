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


import static com.dateapp.dateapp.config.security.EndpointAccessCheckService.checkDataAccessPermission;

@RestController
@CrossOrigin
public class LoginController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserInfoService userInfoService;
    private final LocationService locationService;
    private static final double DEFAULT_DISTANCE = 50;

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
                UserRegisterDto user = userService.findUserById(userId);
                UserInfoDto userInfo = user.getUserInfo();
                System.out.println(userInfo);
                return ResponseEntity.ok().body(userInfo);
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
                return ResponseEntity.badRequest().build();
            }

    }
    @PostMapping("/userinfo")
    ResponseEntity<String> addUserInfo(@RequestBody UserInfoDto userInfoDto){
        try{
            LocationDto locationDto = locationService.save(userInfoDto.getLocationDto()).orElseThrow();
            userInfoDto.setLocationDto(locationDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody UserRegisterDto userRegisterDto) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userRegisterDto.getEmail(), userRegisterDto.getPassword());
            daoAuthenticationProvider.authenticate(authentication);
            UserRegisterDto loggedUser = userService.findUserByEmail(userRegisterDto.getEmail());
            System.out.println(userRegisterDto+ " podane do formularza d=logowania");
            System.out.println("pobrane " + loggedUser );;
            String jwtToken = jwtTokenService.generateJwtToken(loggedUser);
           return ResponseEntity.status(HttpStatus.CREATED).body(jwtToken);
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        try{
            LocationDto locationDto = locationService.save(userRegisterDto.getUserInfo().getLocationDto()).orElseThrow();
            userRegisterDto.getUserInfo().setLocationDto(locationDto);
            userRegisterDto.getUserInfo().setMaxDistance(DEFAULT_DISTANCE);
            Long userInfoId = userInfoService.save(userRegisterDto.getUserInfo());
            userRegisterDto.getUserInfo().setId(userInfoId);
            Long userId = userService.registerUser(userRegisterDto);
            userRegisterDto.setId(userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

