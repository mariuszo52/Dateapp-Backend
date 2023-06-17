package com.dateapp.dateapp.user;

import com.dateapp.dateapp.userRole.UserRole;
import com.dateapp.dateapp.userRole.UserRoleRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserRoleRepository userRoleRepository;

    public UserMapper(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public User map(UserRegisterDto userRegisterDto){
        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        UserRole userRole = userRoleRepository.findByName(userRegisterDto.getUserRole()).orElseThrow(RuntimeException::new);
        user.setUserRole(userRole);
        return user;
    }
    public static UserRegisterDto map(User user){
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail(user.getEmail());
        userRegisterDto.setPassword(user.getPassword());
        userRegisterDto.setUserRole(user.getUserRole().getName());
        return userRegisterDto;
    }
}
