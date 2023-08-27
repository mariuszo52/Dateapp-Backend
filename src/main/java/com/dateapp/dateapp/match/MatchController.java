package com.dateapp.dateapp.match;

import com.dateapp.dateapp.userInfo.UserInfoDto;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/unmatch")
    ResponseEntity<String> deleteMatch(@RequestParam Long chatId){
        try{
            matchService.deleteMatch(chatId);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


