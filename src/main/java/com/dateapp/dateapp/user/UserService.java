package com.dateapp.dateapp.user;

import com.dateapp.dateapp.config.security.LoggedUserService;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import com.dateapp.dateapp.userRole.UserRole;
import com.dateapp.dateapp.userRole.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserMapper userMapper, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Long registerUser(UserRegisterDto userRegisterDto) {
        List<String> allUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(User::getEmail)
                .toList();
        if (allUsers.contains(userRegisterDto.getEmail())) {
            throw new RuntimeException("This email address is already taken");
        }
        else if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
           throw new RuntimeException("Passwords do not match.");
        }else {
            UserRole userRole = userRoleRepository.findByName("USER").orElseThrow(RuntimeException::new);
            userRegisterDto.setUserRole(userRole.getName());
            String encodedPass = passwordEncoder.encode(userRegisterDto.getPassword());
            User user = userMapper.map(userRegisterDto);
            user.setPassword(encodedPass);
            User savedUser = userRepository.save(user);
            return savedUser.getId();
        }
    }
}
