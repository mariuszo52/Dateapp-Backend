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

    UserInfoService(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }
    public Optional<UserInfoDto> getLoggedUserInfo(){
        User user = userRepository.findById(getLoggedUserId()).orElseThrow(UserNotFoundException::new);
        return Optional.of(UserInfoMapper.map(user.getUserInfo()));
    }

    public Long save(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoRepository.save(map(userInfoDto));
        return userInfo.getId();

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
