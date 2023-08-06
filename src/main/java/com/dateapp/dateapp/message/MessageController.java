package com.dateapp.dateapp.message;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@CrossOrigin
@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get-chat-messages")
    public ResponseEntity<Set<MessageDto>> getChatMessages(@RequestParam Long chatId){
        Set<MessageDto> chatMessages = messageService.getChatMessages(chatId);
        return ResponseEntity.ok().body(chatMessages);
    }

}
