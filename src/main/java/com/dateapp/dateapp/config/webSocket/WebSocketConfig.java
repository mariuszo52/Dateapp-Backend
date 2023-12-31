package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.config.webSocket.connectionTicket.TicketRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final TicketRepository ticketRepository;
    private final CustomHandshakeInterceptor customHandshakeInterceptor;

    public WebSocketConfig(TicketRepository ticketRepository, CustomHandshakeInterceptor customHandshakeInterceptor) {
        this.ticketRepository = ticketRepository;
        this.customHandshakeInterceptor = customHandshakeInterceptor;
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setHandshakeHandler(new CustomHandshakeHandler(ticketRepository))
                .addInterceptors(customHandshakeInterceptor)
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
