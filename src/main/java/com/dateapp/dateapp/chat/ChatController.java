package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.config.security.EndpointAccessCheckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dateapp.dateapp.config.security.EndpointAccessCheckService.checkDataAccessPermission;

@CrossOrigin
@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/all-user-chats")
    ResponseEntity<?> getUserChats(@RequestParam long userId) {
        try {
            checkDataAccessPermission(userId);
            return ResponseEntity.ok().body(chatService.getUserChats(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
