package com.dateapp.dateapp.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
@CrossOrigin
@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/all-user-chats")
    ResponseEntity<Set<ChatDto>> getUserChats(@RequestParam long userId){
        /*
        dodac try i catch a takze sprawdzenie czy zalogowany user to user ktory moze pobrac czat
        poprawic serializacje jacksona

         */
        System.out.println(chatService.getUserChats(userId));
        Set<ChatDto> chats = chatService.getUserChats(userId);
        return ResponseEntity.ok().body(chats);
    }
}
