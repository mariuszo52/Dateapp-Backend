package com.dateapp.dateapp.config.webSocket.connectionTicket;

class TicketMapper {
    static TicketDto map(Ticket ticket){
        TicketDto ticketDto = new TicketDto();
        ticketDto.setText(ticket.getText());
        ticketDto.setId(ticket.getId());
        ticketDto.setUserId(ticket.getUser().getId());
        return ticketDto;
    }
}
