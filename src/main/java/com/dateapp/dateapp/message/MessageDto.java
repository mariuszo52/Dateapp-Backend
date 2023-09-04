package com.dateapp.dateapp.message;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private LocalDateTime sendTime;
    @NotNull
    private Long fromUserId;
    @NotNull
    @Size(min = 1 , max = 1000)
    private String text;
    @NotNull
    private Long chatId;
}