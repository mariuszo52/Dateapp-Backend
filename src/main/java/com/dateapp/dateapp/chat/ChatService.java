package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.config.security.LoggedUserService;
import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;


@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMapper chatMapper;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository, ChatMapper chatMapper) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.chatMapper = chatMapper;
    }

    public Chat createChat(MatchDto matchDto) {
        Chat chat = new Chat();
        User user1 = userRepository.findById(matchDto.getUserId()).orElseThrow();
        User user2 = userRepository.findById(matchDto.getMatchedUserId()).orElseThrow(UserNotFoundException::new);
        List<User> participants = new ArrayList<>();
        participants.add(user1);
        participants.add(user2);
        chat.setParticipants(participants);
        return chatRepository.save(chat);

    }


    public TreeSet<ChatDto> getUserChats(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getChats().stream()
                .map(chatMapper::map)
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator.comparing(ChatDto::getLastMessageTime).
                                thenComparingLong(ChatDto::getId).reversed())));
    }
    public void checkDataAccessPermission(long userId){
        if (getLoggedUserId() != userId) {
            throw new UnauthorizedResourceAccessException();
        }
    }
}



