package com.dateapp.dateapp.message;

import com.dateapp.dateapp.chat.ChatDto;
import com.dateapp.dateapp.chat.ChatService;
import com.dateapp.dateapp.chat.ChatUser;
import com.dateapp.dateapp.chat.ChatUserService;
import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@CrossOrigin
@RestController
public class MessageController {
    private final MessageService messageService;
    private final ChatUserService chatUserService;
    private final ChatService chatService;

    public MessageController(MessageService messageService, ChatUserService chatUserService, ChatService chatService) {
        this.messageService = messageService;
        this.chatUserService = chatUserService;
        this.chatService = chatService;
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getChatMessages(@RequestParam Long chatId) {
        try {
            messageService.checkAccessToGetMessages(chatId);
            Set<MessageDto> chatMessages = messageService.getChatMessages(chatId);
            return ResponseEntity.ok().body(chatMessages);
        } catch (UnauthorizedResourceAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/notifications-counter")
    public ResponseEntity<String> updateCounter(@RequestParam Long chatId) {
        try {
            long userId = getLoggedUserId();
            ChatDto chat = chatService.findChatById(chatId);
            Long notificationReceiverId = chat.getParticipantsIds().stream()
                    .filter(id -> !id.equals(userId))
                    .findFirst().orElseThrow();
            ChatUser chatUser = chatUserService.getChatUserEntityByUserIdAndChatId(notificationReceiverId, chatId);
            chatUserService.updateNotificationCounter(chatUser);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/notifications-counter")
    public ResponseEntity<?> getUserChatNotificationCounter(@RequestParam Long chatId) {
        try {
            long userId = getLoggedUserId();
            return ResponseEntity.ok(chatUserService.getUserChatNotificationCounter(userId, chatId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/unread-chats")
    public ResponseEntity<?> getUserUnreadChats() {
        try {
            long userId = getLoggedUserId();
            return ResponseEntity.ok(chatUserService.checkUserUnreadChats(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/notifications-counter-reset")
    public ResponseEntity<String> resetUnreadMessagesCounter(@RequestParam Long chatId) {
        try {
            long userId = getLoggedUserId();
            chatUserService.resetUnreadMessagesCounter(userId, chatId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
