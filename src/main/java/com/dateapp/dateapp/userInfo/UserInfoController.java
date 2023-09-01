package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.config.security.LoggedUserService;
import com.dateapp.dateapp.userInfo.userInfoEdit.UserInfoEditDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PutMapping("/distance")
    public ResponseEntity<?> updateMaxDistance(@RequestParam Double distance) {
        userInfoService.updateUserInfoMaxDistance(distance);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user-info-edit")
    public ResponseEntity<?> getUserInfoEditDto(@Valid @RequestBody UserInfoEditDto userInfoEditDto) {
        try {
            UserInfoDto userInfoDto = userInfoService.updateUserInfo(userInfoEditDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userInfoDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping("/logged-user-info")
    public ResponseEntity<?> getLoggedUserInfo() {
        try {
            UserInfoDto userInfoDto = userInfoService.getLoggedUserInfo().orElseThrow();
            return ResponseEntity.ok().body(userInfoDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logged-user-id")
    public ResponseEntity<?> getLoggedUserId() {
        try {
            return ResponseEntity.ok().body(LoggedUserService.getLoggedUserId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
