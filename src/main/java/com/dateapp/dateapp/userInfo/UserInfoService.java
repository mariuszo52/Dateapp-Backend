package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.chat.Chat;
import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import com.dateapp.dateapp.userInfo.location.Location;
import com.dateapp.dateapp.userInfo.location.LocationRepository;
import com.dateapp.dateapp.userInfo.userInfoEdit.UserInfoEditDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;
import static com.dateapp.dateapp.userInfo.UserInfoMapper.map;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    UserInfoService(UserInfoRepository userInfoRepository, UserRepository userRepository, LocationRepository locationRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    public Optional<UserInfoDto> getLoggedUserInfo() {
        User user = userRepository.findById(getLoggedUserId()).orElseThrow(UserNotFoundException::new);
        return Optional.of(UserInfoMapper.map(user.getUserInfo()));
    }

    public UserInfoDto save(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoRepository.save(map(userInfoDto));
        return UserInfoMapper.map(userInfo);

    }

    @Transactional
    public void updateUserInfoMaxDistance(double distance) {
        long loggedUserId = getLoggedUserId();
        User user = userRepository.findById(loggedUserId).orElseThrow(UserNotFoundException::new);
        user.getUserInfo().setMaxDistance(distance);
    }

    @Transactional
    public UserInfoDto updateUserInfo(UserInfoEditDto userInfoEditDto) {
        User user = userRepository.findById(getLoggedUserId()).orElseThrow(UserNotFoundException::new);
        UserInfo userInfo = user.getUserInfo();
        locationRepository.findByName(userInfoEditDto.getLocationName()).ifPresentOrElse(locationRepository::save, () -> {
            Location location = new Location(userInfoEditDto.getLocationName(), userInfoEditDto.getLocationCountry(),
                    userInfoEditDto.getLocationLatitude(), userInfoEditDto.getLocationLongitude());
            locationRepository.save(location);
            userInfo.setLocation(location);
        });

        userInfo.setFirstName(userInfoEditDto.getFirstName());
        LocalDate dateOfBirth = LocalDate.of(userInfoEditDto.getYearOfBirth(), userInfoEditDto.getMonthOfBirth(), userInfoEditDto.getDayOfBirth());
        userInfo.setDateOfBirth(dateOfBirth);
        userInfo.setUrl(userInfoEditDto.getUrl());
        userInfo.setAbout(userInfoEditDto.getAbout());
        return map(userInfo);

    }

    public void checkUserAccessToChat(Long userId, Long chatId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        boolean hasUserAccess = user.getChats().stream().map(Chat::getId).anyMatch(id -> id.equals(chatId));
        if (!hasUserAccess) {
            throw new UnauthorizedResourceAccessException();
        }
    }
}
