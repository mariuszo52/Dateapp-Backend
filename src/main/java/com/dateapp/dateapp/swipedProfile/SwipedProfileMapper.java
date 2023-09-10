package com.dateapp.dateapp.swipedProfile;

public class SwipedProfileMapper {
    public static SwipedProfileDto map(SwipedProfile swipedProfile) {
        SwipedProfileDto swipedProfileDto = new SwipedProfileDto();
        swipedProfileDto.setUserId(swipedProfile.getUser().getId());
        swipedProfileDto.setSwipedProfileId(swipedProfile.getSwipedProfile().getId());
        swipedProfileDto.setSwipeTime(swipedProfile.getSwipeTime());
        return swipedProfileDto;
    }
}
