package com.dateapp.dateapp.message;

import jakarta.validation.constraints.Min;
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
    @Min(1)
    private Long id;
    private LocalDateTime sendTime;
    @NotNull
    @Min(1)
    private Long fromUserId;
    @NotNull
    @Size(min = 1, max = 1000)
    private String text;
    @NotNull
    @Min(1)
    private Long chatId;
}