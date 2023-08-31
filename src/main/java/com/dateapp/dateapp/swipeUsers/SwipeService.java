package com.dateapp.dateapp.swipeUsers;

import com.dateapp.dateapp.config.security.LoggedUserService;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.match.Match;
import com.dateapp.dateapp.swipedProfile.SwipedProfile;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import com.dateapp.dateapp.userInfo.location.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@Service
public class SwipeService {
    private final UserRepository userRepository;
    private final LocationService locationService;

    SwipeService(UserRepository userRepository, LocationService locationService) {
        this.userRepository = userRepository;
        this.locationService = locationService;
    }


    Set<SwipeCardDto> getUsersToSwipe(long userId, double distance) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Long> matchedUsers = user.getMatches().stream()
                .map(match -> match.getMatchedUser().getId())
                .toList();
        List<User> usersSwiped = user.getSwipedProfiles().stream()
                .map(SwipedProfile::getSwipedProfile)
                .toList();

        return userRepository.findAllByUserInfo_GenderIdentity(user.getUserInfo().getGenderInterest()).stream()
                .filter(userToSwipe -> !matchedUsers.contains(userToSwipe.getId()))
                .filter(userToSwipe -> !usersSwiped.contains(userToSwipe))
                .filter(userToSwipe -> filterUsersByDistance(user, userToSwipe, distance))
                .map(SwipeCardMapper::map)
                .collect(Collectors.toSet());
    }
    private boolean filterUsersByDistance(User user, User userToSwipe, double distance){
        Double userLatitude = user.getUserInfo().getLocation().getLatitude();
        Double userLongitude = user.getUserInfo().getLocation().getLongitude();
        Double userToSwipeLatitude = userToSwipe.getUserInfo().getLocation().getLatitude();
        Double userToSwipeLongitude = userToSwipe.getUserInfo().getLocation().getLongitude();
        double distanceBetweenUsers = locationService.calculateDistanceInMetersBetweenUsers
                (userLatitude, userLongitude, userToSwipeLatitude, userToSwipeLongitude);
        return distanceBetweenUsers <= distance * 1000;
    }

}

