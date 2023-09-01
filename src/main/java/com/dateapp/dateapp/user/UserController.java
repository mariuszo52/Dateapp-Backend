package com.dateapp.dateapp.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/new-password")
    ResponseEntity<String> changeUserPassword(@Valid @RequestBody PasswordChangeDto passwordChangeDto) {
        try {
            userService.changePassword(passwordChangeDto.getOldPassword(), passwordChangeDto.getNewPassword());
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
