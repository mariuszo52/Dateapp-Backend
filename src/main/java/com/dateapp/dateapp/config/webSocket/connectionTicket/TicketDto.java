package com.dateapp.dateapp.config.webSocket.connectionTicket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    @Min(1)
    private Long id;
    @NotNull
    @Size(min = 20, max = 20)
    private String text;
    @Min(1)
    private Long userId;

}
