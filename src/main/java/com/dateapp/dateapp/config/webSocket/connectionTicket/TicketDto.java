package com.dateapp.dateapp.config.webSocket.connectionTicket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDto {
    private Long id;
    private String text;
    private boolean validity;
    private Long userId;

}
