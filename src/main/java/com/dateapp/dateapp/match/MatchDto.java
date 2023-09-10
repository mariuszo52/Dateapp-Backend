package com.dateapp.dateapp.match;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MatchDto {
    private Boolean isMatched;
    private Long userId;
    private LocalDate matchDate;
    private Long matchedUserId;
    private String matchedUserName;
    private String matchedUserUrl;
    private Long chatId;


}
