package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.match.Match;
import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.match.MatchMapper;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MatchMapper matchMapper;

    public ChatService(ChatRepository chatRepository, UserRepository userRepository, MatchMapper matchMapper) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.matchMapper = matchMapper;
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



    public Set<ChatDto> getUserChats(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getChats().stream()
                .map(ChatMapper::map)
                .collect(Collectors.toSet());
    }
}
