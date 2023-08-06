package com.dateapp.dateapp.like;

import com.dateapp.dateapp.swipedProfile.*;
import com.dateapp.dateapp.user.UserRepository;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class LikeService {
    private final UserRepository userRepository;
    private final SwipedProfileRepository swipedProfileRepository;

    LikeService(UserRepository userRepository, SwipedProfileRepository swipedProfileRepository) {
        this.userRepository = userRepository;
        this.swipedProfileRepository = swipedProfileRepository;
    }

    List<UserInfoDto> getAllUserReceivedLikes(long userId) {
        return swipedProfileRepository.findAllBySwipedProfile_IdAndSwipeDirection(userId, SwipedProfileService.RIGHT_SWIPE).stream()
                .map(p -> p.getUser().getUserInfo())
                .map(UserInfoMapper::map)
                .toList();
    }
}
