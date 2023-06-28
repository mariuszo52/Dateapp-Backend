package com.dateapp.dateapp.leftSwiped;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
class LeftSwipeController {
    private final LeftSwipedService leftSwipedService;

    LeftSwipeController(LeftSwipedService leftSwipedService) {
        this.leftSwipedService = leftSwipedService;
    }

    @PostMapping("/swipe-left")
    ResponseEntity<String> addLeftSwipedProfile(@RequestBody LeftSwipedDto leftSwipedDto) {
        try {
            System.out.println("wykonała sue metoda zapisywania lefta z danymi " + leftSwipedDto.getLeftSwipedProfileId() + " "  + leftSwipedDto.getUserId());
            leftSwipedService.save(leftSwipedDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
