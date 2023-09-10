package com.dateapp.dateapp.swipedProfile;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SwipedProfileDto {
    private LocalDateTime swipeTime;
    @NotNull
    private long userId;
    @NotNull
    private long swipedProfileId;


}
