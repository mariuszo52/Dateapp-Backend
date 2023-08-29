package com.dateapp.dateapp.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/new-password")
    ResponseEntity<String> changeUserPassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        try {
            userService.changePassword(oldPassword, newPassword);
            return ResponseEntity.ok().body("Password changed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("delete-account")
    ResponseEntity<String> deleteUserAccount(@RequestParam String password){
        try {
            userService.deleteAccount(password);
            return ResponseEntity.ok().body("User deleted.");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
