package com.dateapp.dateapp.chat;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatUserService {
    private final ChatUserRepository chatUserRepository;

    public ChatUserService(ChatUserRepository chatUserRepository) {
        this.chatUserRepository = chatUserRepository;
    }

    public ChatUser getChatUserEntityByUserIdAndChatId(long userId, long chatId) {
        return chatUserRepository.findByChat_IdAndUserId(chatId, userId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void updateNotificationCounter(ChatUser chatUser) {
        chatUser.setNotifications(chatUser.getNotifications() + 1);
    }

    public long getUserChatNotificationCounter(long userId, long chatId) {
        return chatUserRepository.findByChat_IdAndUserId(chatId, userId).orElseThrow(RuntimeException::new)
                .getNotifications();
    }

    public long checkUserUnreadChats(long userId) {
        List<ChatUser> userChats = chatUserRepository.findByUser_Id(userId);
        return userChats.stream()
                .filter(chat -> chat.getNotifications() > 0)
                .count();
    }

    @Transactional
    public void resetUnreadMessagesCounter(long userId, long chatId) {
        ChatUser chatUser = chatUserRepository.findByChat_IdAndUserId(chatId, userId).orElseThrow();
        chatUser.setNotifications(0L);
    }
}
