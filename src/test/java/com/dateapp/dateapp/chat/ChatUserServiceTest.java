package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ChatUserServiceTest {
    private ChatUserService chatUserService;
    @Mock ChatUserRepository chatUserRepository;
    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp(){
        autoCloseable = openMocks(this);
        chatUserService = new ChatUserService(chatUserRepository);
    }
    @AfterEach
    void close() throws Exception {
        autoCloseable.close();
    }

    @Test
    void shouldReturnNotNull() {
        //given
        long userId = 1;
        long chatId = 1;
        ChatUser chatUser = new ChatUser();
        when(chatUserRepository.findByChat_IdAndUserId(chatId, userId)).thenReturn(Optional.of(chatUser));
        //when
        ChatUser resultChatUser = chatUserService.getChatUserEntityByUserIdAndChatId(userId, chatId);
        //then
        Assertions.assertNotNull(resultChatUser);
    }

    @Test
    void shouldThrowRuntimeExceptionWhenChatUserIsNotFound() {
        //given
        long userId = 1;
        long chatId = 1;
        when(chatUserRepository.findByChat_IdAndUserId(chatId, userId)).thenReturn(Optional.empty());
        //when //then
        assertThrows(RuntimeException.class, ()-> chatUserService.getChatUserEntityByUserIdAndChatId(userId, chatId));
    }
    @Test
    void shouldNotificationsEquals1(){
        //given
        ChatUser chatUser = new ChatUser();
        chatUser.setNotifications(0L);
        //when
        chatUserService.updateNotificationCounter(chatUser);
        //then
        assertEquals(1, chatUser.getNotifications());
    }
    @Test
    void shouldNotificationsEquals2(){
        //given
        ChatUser chatUser = new ChatUser();
        chatUser.setNotifications(0L);
        //when
        chatUserService.updateNotificationCounter(chatUser);
        chatUserService.updateNotificationCounter(chatUser);
        //then
        assertEquals(2, chatUser.getNotifications());
    }
    @Test
    void shouldNotificationsEquals0(){
        //given
        ChatUser chatUser = new ChatUser();
        chatUser.setNotifications(55L);
        //when
        chatUserService.updateNotificationCounter(chatUser);
        //then
        assertEquals(56, chatUser.getNotifications());
    }

    @Test
    void shouldThrowRuntimeExceptionWhenChatUserNotificationIsNotFound() {
        //given
        long userId = 1;
        long chatId = 1;
        when(chatUserRepository.findByChat_IdAndUserId(chatId, userId)).thenReturn(Optional.empty());
        //when //then
        assertThrows(RuntimeException.class, ()-> chatUserService.getUserChatNotificationCounter(userId, chatId));
    }
    @Test
    void shouldReturnNotificationsWithValue155() {
        //given
        long userId = 1;
        long chatId = 1;
        ChatUser chatUser = new ChatUser();
        chatUser.setNotifications(155L);
        when(chatUserRepository.findByChat_IdAndUserId(chatId, userId)).thenReturn(Optional.of(chatUser));
        //when
        long resultNotifications = chatUserService.getUserChatNotificationCounter(userId, chatId);
        //then
        Assertions.assertEquals(155, resultNotifications);
    }

    @Test
    void shouldReturnUnreadChatsValue2() {
        //given
        ChatUser chatUser = new ChatUser();
        chatUser.setUser(new User(1L, "user1@ex.com"));
        chatUser.setNotifications(20L);
        ChatUser chatUser2 = new ChatUser();
        chatUser2.setUser(new User(1L, "user1@ex.com"));
        chatUser2.setNotifications(155L);
        when(chatUserRepository.findByUser_Id(1L)).thenReturn(List.of(chatUser, chatUser2));
        //when
        long resultUnreadChats = chatUserService.checkUserUnreadChats(1L);
        //then
        Assertions.assertEquals(2, resultUnreadChats);
    }

    @Test
    void shouldReturnUnreadChatsValue1() {
        //given
        ChatUser chatUser = new ChatUser();
        chatUser.setUser(new User(1L, "user1@ex.com"));
        chatUser.setNotifications(20L);
        ChatUser chatUser2 = new ChatUser();
        chatUser2.setUser(new User(1L, "user1@ex.com"));
        chatUser2.setNotifications(0L);
        when(chatUserRepository.findByUser_Id(1L)).thenReturn(List.of(chatUser, chatUser2));
        //when
        long resultUnreadChats = chatUserService.checkUserUnreadChats(1L);
        //then
        Assertions.assertEquals(1, resultUnreadChats);
    }

    @Test
    void shouldUserChatNotificationsSetFrom55to0(){
        //given
        long chatId = 1;
        long userId = 1;
        ChatUser chatUser = new ChatUser();
        chatUser.setNotifications(55L);
        when(chatUserRepository.findByChat_IdAndUserId(chatId, userId)).thenReturn(Optional.of(chatUser));
        //when
        chatUserService.resetUnreadMessagesCounter(userId, chatId);
        //then
        assertEquals(0, chatUser.getNotifications());
    }
    @Test
    void shouldUserChatNotificationsSetFrom100to0(){
        //given
        long chatId = 1;
        long userId = 1;
        ChatUser chatUser = new ChatUser();
        chatUser.setNotifications(100L);
        when(chatUserRepository.findByChat_IdAndUserId(chatId, userId)).thenReturn(Optional.of(chatUser));
        //when
        chatUserService.resetUnreadMessagesCounter(userId, chatId);
        //then
        assertEquals(0, chatUser.getNotifications());
    }

}