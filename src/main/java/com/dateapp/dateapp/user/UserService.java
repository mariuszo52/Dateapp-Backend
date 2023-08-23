package com.dateapp.dateapp.user;

import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userInfo.UserInfoRepository;
import com.dateapp.dateapp.userRole.UserRole;
import com.dateapp.dateapp.userRole.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;
    public UserService(UserMapper userMapper, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, UserInfoRepository userInfoRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInfoRepository = userInfoRepository;
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
            UserInfo userInfo = userInfoRepository.findById(userRegisterDto.getUserInfo().getId()).orElseThrow();
            savedUser.setUserInfo(userInfo);

            return savedUser.getId();
        }
    }
   public UserRegisterDto findUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserMapper.map(user);
    }
}
