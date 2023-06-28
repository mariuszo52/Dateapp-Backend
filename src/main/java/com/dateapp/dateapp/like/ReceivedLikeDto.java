package com.dateapp.dateapp.like;

import java.time.LocalDateTime;

class ReceivedLikeDto {
    private Long id;
    private LocalDateTime time;
    private long sendingUserId;
    private long receivingUserId;
    private String userUrl;
    private String userFirstName;

    public ReceivedLikeDto(Long id, LocalDateTime time, long sendingUserId, long receivingUserId, String userUrl, String userFirstName) {
        this.id = id;
        this.time = time;
        this.sendingUserId = sendingUserId;
        this.receivingUserId = receivingUserId;
        this.userUrl = userUrl;
        this.userFirstName = userFirstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public long getSendingUserId() {
        return sendingUserId;
    }

    public void setSendingUserId(long sendingUserId) {
        this.sendingUserId = sendingUserId;
    }

    public long getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(long receivingUserId) {
        this.receivingUserId = receivingUserId;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
}