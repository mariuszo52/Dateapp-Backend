package com.dateapp.dateapp.user;

import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import com.dateapp.dateapp.userRole.UserRole;
import com.dateapp.dateapp.userRole.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserRoleRepository userRoleRepository;

    public UserMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public User map(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setId(userRegisterDto.getId());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        UserRole userRole = userRoleRepository.findByName(userRegisterDto.getUserRole()).orElseThrow(RuntimeException::new);
        user.setUserRole(userRole);
        UserInfo userInfo = UserInfoMapper.map(userRegisterDto.getUserInfo());
        user.setUserInfo(userInfo);
        return user;
    }

    public static UserRegisterDto map(User user) {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setId(user.getId());
        userRegisterDto.setEmail(user.getEmail());
        userRegisterDto.setPassword(user.getPassword());
        UserInfoDto userInfoDto = UserInfoMapper.map(user.getUserInfo());
        userRegisterDto.setUserInfo(userInfoDto);
        return userRegisterDto;
    }
}
