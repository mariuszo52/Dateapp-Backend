package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.match.MatchDto;
import com.dateapp.dateapp.userInfo.UserInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ChatDto {
    private Long id;
    private Set<Long> participantsIds;
    private LocalDate matchDate;

    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private List<MatchDto> matchDtos;
    private Set<Long> messageIds;

}