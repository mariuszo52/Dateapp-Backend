package com.dateapp.dateapp.chat;

import java.util.Set;

class ChatDto {
    private Long id;
    private Set<Long> participantsIds;
    private Set<Long> messageIds;

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
