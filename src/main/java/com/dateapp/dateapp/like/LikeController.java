package com.dateapp.dateapp.like;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RestController
@CrossOrigin
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
   @GetMapping("/likes-received")
    ResponseEntity<List<ReceivedLikeDto>> getAllUserReceivedLikes(@RequestParam long userId){
        try {
            List<ReceivedLikeDto> allUserReceivedLikes = likeService.getAllUserReceivedLikes(userId);
            return ResponseEntity.ok(allUserReceivedLikes);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
