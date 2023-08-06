package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.userInfo.UserInfoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ChatDto {
    private Long id;
    private Set<Long> participantsIds;
    private LocalDate matchDate;

    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private List<MatchDto> matchDtos;
    private Set<Long> messageIds;

    ChatDto() {
    }

    public LocalDateTime getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(LocalDateTime lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    public List<MatchDto> getMatchDtos() {
        return matchDtos;
    }

    public void setMatchDtos(List<MatchDto> matchDtos) {
        this.matchDtos = matchDtos;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatDto chatDto = (ChatDto) o;
        return Objects.equals(id, chatDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "id=" + id +
                ", participantsIds=" + participantsIds +
                ", matchDate=" + matchDate +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastMessageTime=" + lastMessageTime +
                ", matchDtos=" + matchDtos +
                ", messageIds=" + messageIds +
                '}';
    }
}
