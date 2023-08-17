package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.userInfo.userInfoEdit.UserInfoEditDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
