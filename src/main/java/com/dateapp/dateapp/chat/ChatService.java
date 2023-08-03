package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.match.Match;
import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.match.MatchMapper;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createChat(Match match) {
        Chat chat = new Chat();
        Chat chatEntity = chatRepository.save(chat);
        User user1 = userRepository.findById(match.getUser().getId()).orElseThrow(UserNotFoundException::new);
        User user2 = userRepository.findById(match.getMatchedUser().getId()).orElseThrow(UserNotFoundException::new);
        HashSet<User> participants = new HashSet<>();
        participants.add(user1);
        participants.add(user2);
        chatEntity.setParticipants(participants);
        chatEntity.setMatch(match);
    }

    public Optional<ChatDto> findChatById(long chatId) {
        return chatRepository.findById(chatId)
                .map(ChatMapper::map);
    }


    public Set<ChatDto> getUserChats(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getChats().stream()
                .map(ChatMapper::map)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(chatDto -> -chatDto.getId()))));
    }
}
