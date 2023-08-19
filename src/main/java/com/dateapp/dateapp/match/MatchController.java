package com.dateapp.dateapp.match;

import com.dateapp.dateapp.userInfo.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

@RestController
@CrossOrigin
class MatchController {
    private final MatchService matchService;

    MatchController(MatchService matchService) {
        this.matchService = matchService;
    }


    @GetMapping("/all-matches")
    ResponseEntity<?> getAllUserMatches() {
        try {
            ArrayList<UserInfoDto> allUserMatchesInfo = matchService.getAllUserMatchesInfo(getLoggedUserId());
            return ResponseEntity.ok(allUserMatchesInfo);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}


