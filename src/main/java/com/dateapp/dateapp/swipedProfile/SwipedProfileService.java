package com.dateapp.dateapp.swipedProfile;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class SwipedProfileService {
    public static final String LEFT_SWIPE = "LEFT";
    public static final String RIGHT_SWIPE = "RIGHT";

    private final SwipedProfileRepository swipedProfileRepository;
    private final UserRepository userRepository;

    SwipedProfileService(SwipedProfileRepository swipedProfileRepository, UserRepository userRepository) {
        this.swipedProfileRepository = swipedProfileRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public void saveLeftSwipe(SwipedProfileDto leftSwipedDto) {
        User user = userRepository.findById(leftSwipedDto.getUserId()).orElseThrow(UserNotFoundException::new);
        User swipedUser = userRepository.findById(leftSwipedDto.getSwipedProfileId()).orElseThrow(UserNotFoundException::new);
        SwipedProfile swipedProfile = createSwipedProfile(user, swipedUser, LEFT_SWIPE);
        swipedProfileRepository.save(swipedProfile);
    }
    @Transactional
    public void saveRightSwipe(SwipedProfileDto rightSwipedProfile) {
        User user = userRepository.findById(rightSwipedProfile.getUserId()).orElseThrow(UserNotFoundException::new);
        User swipedUser = userRepository.findById(rightSwipedProfile.getSwipedProfileId()).orElseThrow(UserNotFoundException::new);
        SwipedProfile swipedProfile = createSwipedProfile(user, swipedUser, RIGHT_SWIPE);
        swipedProfileRepository.save(swipedProfile);
    }
    @Transactional
    public void deleteMatchedLikes(Long user1Id, Long user2Id){
        swipedProfileRepository.deleteSwipedProfileByUser_IdAndSwipedProfile_Id(user2Id, user1Id);
    }


    private static SwipedProfile createSwipedProfile(User user, User swipedUser, String swipeDirection) {
        SwipedProfile swipedProfile = new SwipedProfile();
        swipedProfile.setUser(user);
        swipedProfile.setSwipeDirection(swipeDirection);
        swipedProfile.setSwipedProfile(swipedUser);
        return swipedProfile;
    }
}
