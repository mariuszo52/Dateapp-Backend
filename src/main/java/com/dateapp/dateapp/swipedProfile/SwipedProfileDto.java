package com.dateapp.dateapp.swipedProfile;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class SwipedProfileDto {
    private LocalDateTime swipeTime;
    @NotNull
    private long userId;
    @NotNull
    private long swipedProfileId;

    public SwipedProfileDto() {
    }


    public LocalDateTime getSwipeTime() {
        return swipeTime;
    }

    public void setSwipeTime(LocalDateTime swipeTime) {
        this.swipeTime = swipeTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSwipedProfileId() {
        return swipedProfileId;
    }

    public void setSwipedProfileId(long swipedProfileId) {
        this.swipedProfileId = swipedProfileId;
    }
}
