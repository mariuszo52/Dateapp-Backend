package com.dateapp.dateapp.match;

import com.dateapp.dateapp.config.security.EndpointAccessCheckService;
import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserService;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dateapp.dateapp.config.security.EndpointAccessCheckService.checkDataAccessPermission;

@RestController
@CrossOrigin
class MatchController {
    private final MatchService matchService;

    MatchController(MatchService matchService) {
        this.matchService = matchService;
    }


    @GetMapping("/all-matches")
    ResponseEntity<?> getAllUserMatches(@RequestParam long userId) {
        try {
            checkDataAccessPermission(userId);
            List<UserInfoDto> allUserMatchesInfo = matchService.getAllUserMatchesInfo(userId);
            return ResponseEntity.ok(allUserMatchesInfo);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}


