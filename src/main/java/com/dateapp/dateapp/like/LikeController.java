package com.dateapp.dateapp.like;

import com.dateapp.dateapp.userInfo.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@RestController
@CrossOrigin
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/likes-received")
    ResponseEntity<?> getAllUserReceivedLikes() {
        try {
            long userId = getLoggedUserId();
            ArrayList<UserInfoDto> allUserReceivedLikes = likeService.getAllUserReceivedLikes(userId);
            return ResponseEntity.ok(allUserReceivedLikes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
