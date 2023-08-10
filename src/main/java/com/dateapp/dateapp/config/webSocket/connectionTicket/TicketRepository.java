package com.dateapp.dateapp.config.webSocket.connectionTicket;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    Optional<Ticket> findByText(String text);
}
