package com.dateapp.dateapp.swipeUsers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@RestController
@CrossOrigin
public class SwipeController {
    private final SwipeService swipeService;

    public SwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @GetMapping("/get-swipe-users")
    ResponseEntity<?> getUsersToSwipe(@RequestParam double distance) {
        try {
            Set<SwipeCardDto> usersToSwipe = swipeService.getUsersToSwipe(getLoggedUserId(), distance);
            return ResponseEntity.ok(usersToSwipe);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}