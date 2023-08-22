package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserService;
import com.dateapp.dateapp.userInfo.userInfoEdit.UserInfoEditDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@RestController
@CrossOrigin
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PutMapping("/user-info-edit")
    public ResponseEntity<?> getUserInfoEditDto(@RequestBody UserInfoEditDto userInfoEditDto){
        try {
            UserInfoDto userInfoDto = userInfoService.updateUserInfo(userInfoEditDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userInfoDto);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
    @GetMapping("/logged-user-info")
    public ResponseEntity<?> getLoggedUserInfo(){
        try {
            UserInfoDto userInfoDto = userInfoService.getLoggedUserInfo().orElseThrow();
            return ResponseEntity.ok().body(userInfoDto);
        }catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
