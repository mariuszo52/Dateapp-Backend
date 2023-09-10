package com.dateapp.dateapp.match;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.chat.ChatRepository;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MatchMapper {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    MatchMapper(UserRepository userRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    public Match map(MatchDto matchDto) {
        Match match = new Match();
        match.setIsMatched(matchDto.getIsMatched());
        User user = userRepository.findById(matchDto.getUserId()).orElseThrow(UserNotFoundException::new);
        match.setUser(user);
        User matchedUser = userRepository.findById(matchDto.getMatchedUserId()).orElseThrow(UserNotFoundException::new);
        match.setMatchedUser(matchedUser);
        if (matchDto.getChatId() != null) {
            Chat chat = chatRepository.findById(matchDto.getChatId()).orElseThrow();
            match.setChat(chat);
        }
        match.setMatchDate(LocalDate.now());
        return match;
    }

    public MatchDto map(Match match) {
        MatchDto matchDto = new MatchDto();
        matchDto.setIsMatched(match.getIsMatched());
        matchDto.setUserId(match.getUser().getId());
        matchDto.setMatchedUserId(match.getMatchedUser().getId());
        matchDto.setMatchDate(LocalDate.now());
        matchDto.setChatId(match.getChat().getId());
        User matchedUser = userRepository.findById(match.getMatchedUser().getId()).orElseThrow(UserNotFoundException::new);
        matchDto.setMatchedUserName(matchedUser.getUserInfo().getFirstName());
        matchDto.setMatchedUserUrl(matchedUser.getUserInfo().getUrl());
        return matchDto;
    }


}
