package com.dateapp.dateapp.swipeUsers;

import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.swipedProfile.SwipedProfile;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class SwipeService {
    private final UserRepository userRepository;

    SwipeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Set<SwipeCardDto> getUsersToSwipe(long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Long> matchedUsers = user.getMatches().stream()
                .map(match -> match.getMatchedUser().getId())
                .toList();
        List<User> usersSwiped = user.getSwipedProfiles().stream()
                .map(SwipedProfile::getSwipedProfile)
                .toList();

        return userRepository.findAllByUserInfo_GenderIdentity(user.getUserInfo().getGenderInterest()).stream()
                .filter(user1 -> !matchedUsers.contains(user1.getId()))
                .filter(user1 -> !usersSwiped.contains(user1))
                .map(SwipeCardMapper::map)
                .collect(Collectors.toSet());
    }
}
