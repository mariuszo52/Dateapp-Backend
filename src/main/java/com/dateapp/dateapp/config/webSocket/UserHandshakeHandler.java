package com.dateapp.dateapp.config.webSocket;

import com.dateapp.dateapp.exceptions.UserNotFoundException;
import com.dateapp.dateapp.user.User;
import com.dateapp.dateapp.user.UserRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
@Component
public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final UserRepository userRepository;

    UserHandshakeHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
