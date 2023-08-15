package com.dateapp.dateapp.like;

import com.dateapp.dateapp.swipedProfile.*;
import com.dateapp.dateapp.user.UserRepository;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.dateapp.dateapp.swipedProfile.SwipedProfileService.RIGHT_SWIPE;

@Service
public class LikeService {
    private final SwipedProfileRepository swipedProfileRepository;

    LikeService(SwipedProfileRepository swipedProfileRepository) {
        this.swipedProfileRepository = swipedProfileRepository;
    }

    ArrayList<UserInfoDto> getAllUserReceivedLikes(long userId) {
        return swipedProfileRepository.findAllBySwipedProfile_IdAndSwipeDirection(userId, RIGHT_SWIPE).stream()
                .sorted(Comparator.comparing(SwipedProfile::getSwipeTime).reversed())
                .map(p -> p.getUser().getUserInfo())
                .map(UserInfoMapper::map)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
