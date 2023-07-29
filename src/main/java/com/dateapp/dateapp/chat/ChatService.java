package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public void createChat(MatchDto matchDto) {
        Chat chat = new Chat();
        Chat chatEntity = chatRepository.save(chat);
        User user1 = userRepository.findById(matchDto.getUserId()).orElseThrow(UserNotFoundException::new);
        User user2 = userRepository.findById(matchDto.getMatchedUserId()).orElseThrow(UserNotFoundException::new);
        HashSet<User> participants = new HashSet<>();
        participants.add(user1);
        participants.add(user2);
        chatEntity.setParticipants(participants);
    }

    public Set<ChatDto> getUserChats(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getChats().stream()
                .map(ChatMapper::map)
                .collect(Collectors.toSet());
    }
}
