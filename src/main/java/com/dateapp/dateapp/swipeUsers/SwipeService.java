package com.dateapp.dateapp.swipeUsers;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.leftSwiped.LeftSwipedProfile;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserMapper;
import com.dateapp.dateapp.user.UserRegisterDto;
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
        List<User> usersLeftSwiped = user.getLeftSwipedProfile().stream()
                .map(LeftSwipedProfile::getLeftSwipedProfile)
                .toList();
        System.out.println("left swipped " + usersLeftSwiped.size());
        return userRepository.findAllByUserInfo_GenderIdentity(user.getUserInfo().getGenderInterest()).stream()
                .filter(user1 -> !usersLeftSwiped.contains(user1))
                .map(SwipeCardMapper::map)
                .collect(Collectors.toSet());
    }
}
