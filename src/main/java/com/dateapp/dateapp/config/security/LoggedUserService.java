package com.dateapp.dateapp.config.security;

import com.dateapp.dateapp.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUserService {

    public static long getLoggedUserId(){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return user.getId();

    }
}
