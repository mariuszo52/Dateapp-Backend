package com.dateapp.dateapp.userInfo;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;

    UserInfoService(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUserInfo(UserInfoDto userInfoDto) {
        UserInfo userInfo = userInfoRepository.save(UserInfoMapper.map(userInfoDto));
        User user = userRepository.findById(userInfoDto.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setUserInfo(userInfo);
    }
}
