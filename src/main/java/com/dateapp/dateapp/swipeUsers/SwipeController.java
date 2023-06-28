package com.dateapp.dateapp.swipeUsers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin
public class SwipeController {
    private final SwipeService swipeService;

    public SwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @GetMapping("/get-swipe-users")
    ResponseEntity<?> getUsersToSwipe(@RequestParam long userId) {
        try {
            Set<SwipeCardDto> usersToSwipe = swipeService.getUsersToSwipe(userId);
            return ResponseEntity.ok(usersToSwipe);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
