package com.dateapp.dateapp.match;

import java.time.LocalDate;

public class MatchDto {
    private Boolean isMatched;
    private Long userId;
    private LocalDate matchDate;
    private Long matchedUserId;
    private String matchedUserName;
    private String matchedUserUrl;
    private Long chatId;

    public String getMatchedUserName() {
        return matchedUserName;
    }

    public void setMatchedUserName(String matchedUserName) {
        this.matchedUserName = matchedUserName;
    }

    public String getMatchedUserUrl() {
        return matchedUserUrl;
    }

    public void setMatchedUserUrl(String matchedUserUrl) {
        this.matchedUserUrl = matchedUserUrl;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }


    public Boolean getMatched() {
        return isMatched;
    }

    public void setMatched(Boolean matched) {
        isMatched = matched;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMatchedUserId() {
        return matchedUserId;
    }

    public void setMatchedUserId(Long matchedUserId) {
        this.matchedUserId = matchedUserId;
    }
}
