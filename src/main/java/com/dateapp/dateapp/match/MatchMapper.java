package com.dateapp.dateapp.match;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
class MatchMapper {
    private final UserRepository userRepository;

    MatchMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     Match map(MatchDto matchDto){
        Match match = new Match();
        match.setMatched(matchDto.getMatched());
        User user = userRepository.findById(matchDto.getUserId()).orElseThrow(UserNotFoundException::new);
        match.setUser(user);
        User matchedUser = userRepository.findById(matchDto.getMatchedUserId()).orElseThrow(UserNotFoundException::new);
        match.setMatchedUser(matchedUser);
        return match;
    }


}
