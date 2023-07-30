package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.message.MessageDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
class WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void notifyFrontend(MessageDto messageDto){
        simpMessagingTemplate.convertAndSend("/topic/" + messageDto.getChatId(), messageDto);
    }
}
