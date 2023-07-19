package com.dateapp.dateapp.message;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message send(Message message) {
        return new Message(message.getFrom(), message.getText());
    }


}
