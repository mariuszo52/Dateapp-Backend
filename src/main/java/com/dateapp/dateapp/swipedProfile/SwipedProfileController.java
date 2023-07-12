package com.dateapp.dateapp.swipedProfile;

import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.match.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dateapp.dateapp.swipedProfile.SwipedProfileService.LEFT_SWIPE;
import static com.dateapp.dateapp.swipedProfile.SwipedProfileService.RIGHT_SWIPE;

@RestController
@CrossOrigin
class SwipedProfileController {
    private final SwipedProfileService swipedProfileService;
    private final MatchService matchService;

    SwipedProfileController(SwipedProfileService swipedProfileService, MatchService matchService) {
        this.swipedProfileService = swipedProfileService;
        this.matchService = matchService;
    }

    @PostMapping("/swipe-left")
    ResponseEntity<String> addLeftSwipedProfile(@RequestBody SwipedProfileDto leftSwipedProfile) {
        try {
            if (matchService.checkMatch(leftSwipedProfile, RIGHT_SWIPE)) {
                MatchDto matchDto = createMatchDto(leftSwipedProfile, false);
                matchService.saveMatch(matchDto);
                swipedProfileService.deleteMatchedLikes(leftSwipedProfile.getUserId(), leftSwipedProfile.getSwipedProfileId());

            } else {
                swipedProfileService.saveLeftSwipe(leftSwipedProfile);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/swipe-right")
    ResponseEntity<String> addRightSwipedProfile(@RequestBody SwipedProfileDto rightSwipedProfile) {
        try {
            if (matchService.checkMatch(rightSwipedProfile, LEFT_SWIPE )) {
                MatchDto matchDto = createMatchDto(rightSwipedProfile, false);
                matchService.saveMatch(matchDto);
            }
            else if(matchService.checkMatch(rightSwipedProfile, RIGHT_SWIPE)) {
                MatchDto matchDto = createMatchDto(rightSwipedProfile, true);
                matchService.saveMatch(matchDto);
                swipedProfileService.deleteMatchedLikes(rightSwipedProfile.getUserId(), rightSwipedProfile.getSwipedProfileId());
            }
            else {
                swipedProfileService.saveRightSwipe(rightSwipedProfile);
            }
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private static MatchDto createMatchDto(SwipedProfileDto swipedProfile, boolean isMatched) {
        MatchDto matchDto = new MatchDto();
        matchDto.setMatched(isMatched);
        matchDto.setUserId(swipedProfile.getUserId());
        matchDto.setMatchedUserId(swipedProfile.getSwipedProfileId());
        return matchDto;
    }
}