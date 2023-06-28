package com.dateapp.dateapp.leftSwiped;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
class LeftSwipedService {
    private final LeftSwipedRepository leftSwipedRepository;
    private final UserRepository userRepository;

    LeftSwipedService(LeftSwipedRepository leftSwipedRepository, UserRepository userRepository) {
        this.leftSwipedRepository = leftSwipedRepository;
        this.userRepository = userRepository;
    }

    void save(LeftSwipedDto leftSwipedDto){
        User user = userRepository.findById(leftSwipedDto.getUserId()).orElseThrow(UserNotFoundException::new);
        User swipedUser = userRepository.findById(leftSwipedDto.getLeftSwipedProfileId()).orElseThrow(UserNotFoundException::new);
        LeftSwipedProfile leftSwipedProfile = new LeftSwipedProfile();
        leftSwipedProfile.setUser(user);
        leftSwipedProfile.setLeftSwipedProfile(swipedUser);
        leftSwipedRepository.save(leftSwipedProfile);
    }
}
