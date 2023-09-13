package com.dateapp.dateapp.chat;

import com.dateapp.dateapp.match.MatchDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    @Min(1)
    private Long id;
    private Set<Long> participantsIds;
    private LocalDate matchDate;
    @Size(min = 1)
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private List<MatchDto> matchDtos;
    private Set<Long> messageIds;

}