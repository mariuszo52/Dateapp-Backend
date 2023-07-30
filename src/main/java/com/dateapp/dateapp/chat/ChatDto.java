package com.dateapp.dateapp.chat;

import java.time.LocalDate;
import java.util.Set;

public class ChatDto {
    private Long id;
    private Set<Long> participantsIds;
    private LocalDate matchDate;
    private Long matchId;
    private Set<Long> messageIds;

    ChatDto() {
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getParticipantsIds() {
        return participantsIds;
    }

    public void setParticipantsIds(Set<Long> participantsIds) {
        this.participantsIds = participantsIds;
    }

    public Set<Long> getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(Set<Long> messageIds) {
        this.messageIds = messageIds;
    }
}
