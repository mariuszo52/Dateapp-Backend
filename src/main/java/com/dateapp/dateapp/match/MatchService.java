package com.dateapp.dateapp.match;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.chat.ChatDto;
import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.swipedProfile.SwipedProfileDto;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MatchService {
    private final MatchMapper matchMapper;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public MatchService(MatchMapper matchMapper, MatchRepository matchRepository, UserRepository userRepository) {
        this.matchMapper = matchMapper;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }
    public MatchDto getMatchById(long id){
        return matchRepository.findById(id)
                .map(matchMapper::map).orElseThrow();

    }

    @Transactional
    public Match saveMatch(MatchDto matchDto) {
        Match match = matchMapper.map(matchDto);
        MatchDto matchDto1 = new MatchDto();
        matchDto1.setUserId(matchDto.getMatchedUserId());
        matchDto1.setMatchedUserId(matchDto.getUserId());
        matchDto1.setMatched(true);
        Match match1 = matchMapper.map(matchDto1);
        matchRepository.save(match);
        matchRepository.save(match1);
        return match;
    }

    public boolean checkMatch(SwipedProfileDto rightSwipedProfile, String swipeDirection) {
        User user2 = userRepository.findById(rightSwipedProfile.getSwipedProfileId()).orElseThrow(UserNotFoundException::new);
        List<Long> user2Likes = user2.getSwipedProfiles().stream()
                .filter(swipedProfile -> swipedProfile.getSwipeDirection().equals(swipeDirection))
                .map(swipedProfile -> swipedProfile.getSwipedProfile().getId())
                .toList();
        return user2Likes.contains(rightSwipedProfile.getUserId());
    }

    public List<UserInfoDto> getAllUserMatchesInfo(Long userId) {
        return matchRepository.findAllByUser_Id(userId).stream()
                .filter(match -> match.getMatched().equals(true))
                .map(Match::getMatchedUser)
                .map(User::getUserInfo)
                .map(UserInfoMapper::map)
                .collect(Collectors.toList());


    }
}

