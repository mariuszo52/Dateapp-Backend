package com.dateapp.dateapp.user;

import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userInfo.UserInfoRepository;
import com.dateapp.dateapp.userRole.UserRole;
import com.dateapp.dateapp.userRole.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserRoleRepository userRoleRepository;
    private final UserInfoRepository userInfoRepository;

    public UserMapper(UserRoleRepository userRoleRepository, UserInfoRepository userInfoRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public User map(UserRegisterDto userRegisterDto){
        User user = new User();
        user.setId(user.getId());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        UserRole userRole = userRoleRepository.findByName(userRegisterDto.getUserRole()).orElseThrow(RuntimeException::new);
        user.setUserRole(userRole);
        //dodac userinfo
        return user;
    }
    public static UserRegisterDto map(User user){
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setId(user.getId());
        userRegisterDto.setEmail(user.getEmail());
        userRegisterDto.setPassword(user.getPassword());
        return userRegisterDto;
    }
}
