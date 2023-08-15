package com.dateapp.dateapp.config.webSocket.connectionTicket;

import com.dateapp.dateapp.exceptions.user.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    private final static String AVAILABLE_CHARS = "1234567890qwertyuiopasdfghjklzxcvbnm";
    private final static int TICKET_LENGTH = 20;

    public TicketService(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }


    public TicketDto generateTicket(Long userId){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < TICKET_LENGTH ; i++){
            int index = secureRandom.nextInt(AVAILABLE_CHARS.length());
            stringBuilder.append(AVAILABLE_CHARS.charAt(index));
        }
        String ticketText = stringBuilder.toString();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Ticket ticket  = ticketRepository.save(new Ticket(ticketText, user));
        return TicketMapper.map(ticket);
    }

    public boolean checkTicket (String ticketText) {
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .anyMatch(ticket -> ticket.getText().equals(ticketText));
    }


}
