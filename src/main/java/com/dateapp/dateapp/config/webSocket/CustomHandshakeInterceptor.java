package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.config.webSocket.connectionTicket.Ticket;
import com.dateapp.dateapp.config.webSocket.connectionTicket.TicketRepository;
import lombok.NonNull;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
import java.util.stream.StreamSupport;

@Service
public class CustomHandshakeInterceptor implements HandshakeInterceptor {
    private final TicketRepository ticketRepository;

    public CustomHandshakeInterceptor(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, @NonNull Map<String, Object> attributes) {
        String query = request.getURI().getRawQuery();
        String ticketText = query.substring(11);
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .map(Ticket::getText)
                .anyMatch(text -> text.equals(ticketText));
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, @NonNull ServerHttpResponse response, @NonNull WebSocketHandler wsHandler, Exception exception) {
        String query = request.getURI().getRawQuery();
        String ticketText = query.substring(11);
        Ticket ticket = ticketRepository.findByText(ticketText).orElseThrow();
        ticketRepository.delete(ticket);
    }
}
