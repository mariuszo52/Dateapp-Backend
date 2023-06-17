package com.dateapp.dateapp.user;

import com.dateapp.dateapp.userRole.UserRole;
import com.dateapp.dateapp.userRole.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Optional<UserRegisterDto> findUserByCredentials(String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password)
                .map(UserMapper::map);
    }
    @Transactional
    public void registerUser(UserRegisterDto userRegisterDto){
        UserRole userRole = new UserRole();
        userRole.setId(1L);
        userRole.setName("USER");
        userRegisterDto.setUserRole(userRole.getName());

        userRoleRepository.save(userRole);
        String encodedPass = passwordEncoder.encode(userRegisterDto.getPassword());
        User user = userMapper.map(userRegisterDto);
        user.setPassword(encodedPass);

        userRepository.save(user);
    }
}
