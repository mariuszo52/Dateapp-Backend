package com.dateapp.dateapp.userInfo;

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
    @Transactional
    public void addUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo1 = map(userInfoDto);

        UserInfo userInfo = userInfoRepository.save(userInfo1);

        User user = userRepository.findById(userInfoDto.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setUserInfo(userInfo);
    }

    public Optional<UserInfoDto> getUserInfoByUserId(long id){
        return userInfoRepository.findByUserId(id)
                .map(UserInfoMapper::map);
    }
    @Transactional
    public UserInfoDto updateUserInfo(UserInfoEditDto userInfoEditDto){
        User user = userRepository.findById(getLoggedUserId()).orElseThrow(UserNotFoundException::new);
        UserInfo userInfo = user.getUserInfo();
        userInfo.setFirstName(userInfoEditDto.getFirstName());
///////////////////////////////////
        LocalDate dateOfBirth = LocalDate.of(userInfoEditDto.getYearOfBirth(),
                userInfoEditDto.getMonthOfBirth(),
                userInfoEditDto.getDayOfBirth());
        userInfo.setDateOfBirth(dateOfBirth);
        userInfo.setUrl(userInfoEditDto.getUrl());
        userInfo.setAbout(userInfoEditDto.getAbout());
        return map(userInfo);

    }
}
