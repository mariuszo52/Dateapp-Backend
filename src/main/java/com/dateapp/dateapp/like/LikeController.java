package com.dateapp.dateapp.like;

import com.dateapp.dateapp.config.security.EndpointAccessCheckService;
import com.dateapp.dateapp.swipedProfile.SwipedProfileDto;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.dateapp.dateapp.config.security.EndpointAccessCheckService.checkDataAccessPermission;

@RestController
@CrossOrigin
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
   @GetMapping("/likes-received")
    ResponseEntity<?> getAllUserReceivedLikes(@RequestParam long userId){
        try {
            checkDataAccessPermission(userId);
            ArrayList<UserInfoDto> allUserReceivedLikes = likeService.getAllUserReceivedLikes(userId);
            return ResponseEntity.ok(allUserReceivedLikes);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }




}
