package com.dateapp.dateapp.user;

import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.match.MatchRepository;
import com.dateapp.dateapp.swipedProfile.SwipedProfileRepository;
import com.dateapp.dateapp.userInfo.UserInfo;
import com.dateapp.dateapp.userInfo.UserInfoMapper;
import com.dateapp.dateapp.userInfo.UserInfoRepository;
import com.dateapp.dateapp.userInfo.location.Location;
import com.dateapp.dateapp.userInfo.location.LocationRepository;
import com.dateapp.dateapp.userRole.UserRole;
import com.dateapp.dateapp.userRole.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@Service
public class UserService {
    private static final double DEFAULT_DISTANCE = 50;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;
    private final LocationRepository locationRepository;
    private final MatchRepository matchRepository;
    private final SwipedProfileRepository swipedProfileRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository,
                       UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder,
                       UserInfoRepository userInfoRepository, LocationRepository locationRepository,
                       MatchRepository matchRepository, SwipedProfileRepository swipedProfileRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userInfoRepository = userInfoRepository;
        this.locationRepository = locationRepository;
        this.matchRepository = matchRepository;
        this.swipedProfileRepository = swipedProfileRepository;
    }

    @Transactional
    public void registerUser(UserRegisterDto userRegisterDto) {
        List<String> allUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(User::getEmail)
                .toList();
        if (allUsers.contains(userRegisterDto.getEmail())) {
            throw new RuntimeException("This email address is already taken");
        } else if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match.");
        } else {
            UserRole userRole = userRoleRepository.findByName("USER").orElseThrow(RuntimeException::new);
            userRegisterDto.setUserRole(userRole.getName());
            String encodedPass = passwordEncoder.encode(userRegisterDto.getPassword());
            User user = userMapper.map(userRegisterDto);
            user.setPassword(encodedPass);
            UserInfo userInfo = UserInfoMapper.map(userRegisterDto.getUserInfo());
            userInfo.setMaxDistance(DEFAULT_DISTANCE);
            locationRepository.findByName(userInfo.getLocation().getName()).ifPresentOrElse(userInfo::setLocation, () -> {
                Location location = new Location(userInfo.getLocation().getName(), userInfo.getLocation().getCountry(),
                        userInfo.getLocation().getLatitude(), userInfo.getLocation().getLongitude());
                locationRepository.save(location);
                userInfo.setLocation(location);
            });
            UserInfo userInfoEntity = userInfoRepository.save(userInfo);
            user.setUserInfo(userInfoEntity);
            userRepository.save(user);
        }
    }

    public UserRegisterDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserMapper.map(user);
    }

    public UserRegisterDto findUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserMapper.map(user);
    }

    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        User user = userRepository.findById(getLoggedUserId()).orElseThrow(UserNotFoundException::new);
        boolean matches = passwordEncoder.matches(oldPassword, user.getPassword());
        if (matches) {
            user.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new RuntimeException("Current password is incorrect.");

        }
    }

    @Transactional
    public void deleteAccount(String password) {
        User user = userRepository.findById(getLoggedUserId()).orElseThrow(UserNotFoundException::new);
        boolean isPasswordCorrect = passwordEncoder.matches(password, user.getPassword());
        if (isPasswordCorrect) {
            swipedProfileRepository.findAllByUser_IdOrSwipedProfile_Id(user.getId(), user.getId())
                    .forEach(swipedProfile -> {
                        swipedProfile.setUser(null);
                        swipedProfile.setSwipedProfile(null);
                        swipedProfileRepository.deleteById(swipedProfile.getId());
                    });
            matchRepository.findAllByUser_IdOrMatchedUserId(user.getId(), user.getId()).forEach(match -> {
                match.setUser(null);
                match.setMatchedUser(null);
                matchRepository.deleteById(match.getId());
            });
            userRepository.deleteById(user.getId());
        } else {
            throw new RuntimeException("Failed to delete user.");
        }
    }
}
