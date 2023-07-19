package com.dateapp.dateapp.match;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserService;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
class MatchController {
    private final MatchService matchService;
    private final UserService userService;

    MatchController(MatchService matchService, UserService userService) {
        this.matchService = matchService;
        this.userService = userService;
    }


    @GetMapping("/all-matches")
    ResponseEntity<?> getAllUserMatches(@RequestParam long userId) {
        try {
            User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                    .orElseThrow(UserNotFoundException::new);
            if (user.getId() == userId) {
                List<UserInfoDto> allUserMatchesInfo = matchService.getAllUserMatchesInfo(userId);
                return ResponseEntity.ok(allUserMatchesInfo);
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
