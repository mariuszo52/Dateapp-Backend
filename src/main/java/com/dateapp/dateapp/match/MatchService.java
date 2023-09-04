package com.dateapp.dateapp.match;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.chat.ChatRepository;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.swipedProfile.SwipedProfileDto;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class MatchService {
    private final MatchMapper matchMapper;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public MatchService(MatchMapper matchMapper, MatchRepository matchRepository, UserRepository userRepository, ChatRepository chatRepository) {
        this.matchMapper = matchMapper;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    public void saveMatch(MatchDto matchDto, Chat chat) {
        matchDto.setChatId(chat.getId());
        Match match = matchMapper.map(matchDto);
        MatchDto matchDto1 = new MatchDto();
        matchDto1.setUserId(matchDto.getMatchedUserId());
        matchDto1.setMatchedUserId(matchDto.getUserId());
        matchDto1.setIsMatched(true);
        matchDto1.setMatchDate(LocalDate.now());
        matchDto1.setChatId(chat.getId());
        Match match1 = matchMapper.map(matchDto1);
        matchRepository.save(match);
        matchRepository.save(match1);
    }

    public void saveMissMatch(MatchDto matchDto) {
        Match match = matchMapper.map(matchDto);
        MatchDto matchDto1 = new MatchDto();
        matchDto1.setUserId(matchDto.getMatchedUserId());
        matchDto1.setMatchedUserId(matchDto.getUserId());
        matchDto1.setIsMatched(false);
        matchDto1.setMatchDate(LocalDate.now());
        Match match1 = matchMapper.map(matchDto1);
        matchRepository.save(match);
        matchRepository.save(match1);
    }

    public boolean checkMatch(SwipedProfileDto rightSwipedProfile, String swipeDirection) {
        User user2 = userRepository.findById(rightSwipedProfile.getSwipedProfileId()).orElseThrow(UserNotFoundException::new);
        List<Long> user2Likes = user2.getSwipedProfiles().stream()
                .filter(swipedProfile -> swipedProfile.getSwipeDirection().equals(swipeDirection))
                .map(swipedProfile -> swipedProfile.getSwipedProfile().getId())
                .toList();
        return user2Likes.contains(rightSwipedProfile.getUserId());
    }

    public ArrayList<UserInfoDto> getAllUserMatchesInfo(Long userId) {
        return matchRepository.findAllByUser_Id(userId).stream()
                .filter(match -> match.getIsMatched().equals(true))
                .sorted(Comparator.comparing(Match::getMatchDate).reversed())
                .map(match -> userRepository.findById(match.getMatchedUser().getId()).orElseThrow(UserNotFoundException::new))
                .map(User::getUserInfo)
                .map(UserInfoMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));


    }

    @Transactional
    public void deleteMatch(Long chatId) {
        matchRepository.findMatchByChatId(chatId)
                .forEach(match ->{
                    match.setIsMatched(false);
                    match.setChat(null);
                });
        chatRepository.deleteById(chatId);

    }
}

