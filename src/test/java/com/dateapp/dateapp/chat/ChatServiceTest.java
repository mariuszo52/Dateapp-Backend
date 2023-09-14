package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.exceptions.chat.ChatNotFoundException;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ChatServiceTest {
@Mock UserRepository userRepository;
@Mock ChatRepository chatRepository;
@Mock ChatMapper chatMapper;
private ChatService chatService;
private AutoCloseable autoCloseable;
private User user1;
private User user2;
    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        chatService = new ChatService(chatRepository, userRepository, chatMapper);
        user1 = new User();
        user1.setId(1L);
        user2 = new User();
        user2.setId(2L);
    }
    @AfterEach
    void close() throws Exception {
        autoCloseable.close();
    }

    @Test
    void isChatCreated(){
        //given
        MatchDto matchDto = new MatchDto();
        matchDto.setUserId(1L);
        matchDto.setMatchedUserId(2L);
        Chat chat = new Chat();
        when(userRepository.findById(matchDto.getUserId())).thenReturn(Optional.of(user1));
        when(userRepository.findById(matchDto.getMatchedUserId())).thenReturn(Optional.of(user2));
        when(chatRepository.save(any(Chat.class))).thenReturn(chat);
        //when
        Chat resultChat = chatService.createChat(matchDto);
        //then
        Assertions.assertNotNull(resultChat);
        verify(userRepository, times(2)).findById(any(Long.class));
        verify(chatRepository).save(any(Chat.class));
    }

    @Test
    void shouldThrowUserNotFindException(){
        //given
        MatchDto matchDto = new MatchDto();
        matchDto.setUserId(1L);
        matchDto.setMatchedUserId(2L);
        Chat chat = new Chat();
        when(userRepository.findById(matchDto.getUserId())).thenReturn(Optional.empty());
        when(userRepository.findById(matchDto.getMatchedUserId())).thenReturn(Optional.of(user2));
        when(chatRepository.save(any(Chat.class))).thenReturn(chat);
        //when //then
        assertThrows(UserNotFoundException.class, () -> chatService.createChat(matchDto));
    }

    @Test
    void shouldThrowChatNotFoundException() {
        //given
       when(chatRepository.findById(user1.getId())).thenReturn(Optional.empty());
       //when //then
        assertThrows(ChatNotFoundException.class, () -> chatService.findChatById(user1.getId()));
    }

    @Test
    void shouldReturnNotNullChatDto() {
        //given
        Chat chat = new Chat();
        chat.setId(1L);
        ChatDto chatDto = new ChatDto();
        chatDto.setId(1L);
        chatDto.setParticipantsIds(Set.of(user1.getId(), user2.getId()));
        chatDto.setMatchDate(LocalDate.of(2023, 1, 1));
        chatDto.setLastMessage("lastMessage");
        chatDto.setLastMessageTime(LocalDateTime.MIN);
        chatDto.setMessageIds(Set.of(1L, 2L));
        MatchDto matchDto = new MatchDto();
        matchDto.setIsMatched(true);
        matchDto.setUserId(user1.getId());
        matchDto.setMatchDate(LocalDate.MIN);
        matchDto.setMatchedUserId(user2.getId());
        matchDto.setMatchedUserName("user2");
        matchDto.setChatId(chat.getId());
        chatDto.setMatchDtos(List.of(matchDto));
        when(chatRepository.findById(chat.getId())).thenReturn(Optional.of(chat));
        when(chatMapper.map(chat)).thenReturn(chatDto);
        //when
        ChatDto resultChat = chatService.findChatById(user1.getId());
        //then
        assertNotNull(resultChat);
    }
}