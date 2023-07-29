package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.message.Message;
import com.dateapp.dateapp.message.MessageController;
import com.dateapp.dateapp.message.MessageDto;
import com.dateapp.dateapp.message.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class WSController {
    private final WebSocketService webSocketService;
    private final MessageService messageService;

    public WSController(WebSocketService webSocketService, MessageService messageService) {
        this.webSocketService = webSocketService;
        this.messageService = messageService;
    }
    @PostMapping("send-message")
    public void sendMessage(@RequestBody MessageDto message){
        try {
            webSocketService.notifyFrontend(message);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        messageService.save(message);

    }
    @MessageMapping("/chat")
    public MessageDto getMessage(MessageDto message){
        return message;
    }

}

