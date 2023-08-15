package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.config.webSocket.connectionTicket.Ticket;
import com.dateapp.dateapp.config.webSocket.connectionTicket.TicketRepository;
import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;
import com.sun.security.auth.UserPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.Random;
import java.util.stream.StreamSupport;

@Service
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    private final TicketRepository ticketRepository;

    CustomHandshakeHandler(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String query = request.getURI().getRawQuery();
        String ticketText = query.substring(11);
        Ticket ticket = ticketRepository.findByText(ticketText).orElseThrow();
        return new UserPrincipal(Long.toString(ticket.getUser().getId()));
    }


}
