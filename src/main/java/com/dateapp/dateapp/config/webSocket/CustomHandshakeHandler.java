package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.config.webSocket.connectionTicket.TicketRepository;
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
    protected boolean isValidOrigin(ServerHttpRequest request) {
        return checkTicket(request);

    }

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {


        Random random = new Random();
        long randomId = random.nextLong(1, 1000000000);
        return new UserPrincipal(Long.toString(randomId));
    }

    private boolean checkTicket(ServerHttpRequest request) {
        String query = request.getURI().getRawQuery();
        String ticketValue = query.substring(12);
        System.out.println("WS query " + query);
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .anyMatch(ticket -> ticket.getValue().equals(ticketValue));
    }
}
