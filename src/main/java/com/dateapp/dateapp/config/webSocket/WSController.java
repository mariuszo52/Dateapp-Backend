package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.config.webSocket.connectionTicket.Ticket;
import com.dateapp.dateapp.config.webSocket.connectionTicket.TicketService;
import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import com.dateapp.dateapp.exceptions.chat.ChatNotFoundException;
import com.dateapp.dateapp.exceptions.message.MessageException;
import com.dateapp.dateapp.message.MessageDto;
import com.dateapp.dateapp.message.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WSController {
    private final static String MESSAGE_SENT = "Message has sent correctly.";
    private final WebSocketService webSocketService;
    private final MessageService messageService;
    private final TicketService ticketService;

    public WSController(WebSocketService webSocketService, MessageService messageService, TicketService ticketService) {
        this.webSocketService = webSocketService;
        this.messageService = messageService;
        this.ticketService = ticketService;
    }

    @PostMapping("send-message")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto message) {
        try {
            messageService.checkPermissionToSend(message);
            messageService.checkDurationMessage(message);
            webSocketService.notifyFrontend(message);
            messageService.save(message);
            return ResponseEntity.ok().body(MESSAGE_SENT);
        } catch (MessageException | ChatNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UnauthorizedResourceAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
        @PostMapping("ws-ticket")
        public ResponseEntity<Ticket> getAndSaveTicket(@RequestParam Long userId) {
        return ResponseEntity.ok().body(ticketService.generateTicket(userId));

        }




    @MessageMapping("/chat")
    public MessageDto getMessage(MessageDto message) {
        return message;
    }

}

