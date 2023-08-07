package com.dateapp.dateapp.config.security;

import com.dateapp.dateapp.exceptions.UnauthorizedResourceAccessException;

import static com.dateapp.dateapp.config.security.LoggedUserService.getLoggedUserId;

public class EndpointAccessCheckService {
    public static void checkDataAccessPermission(long userId){
        if (getLoggedUserId() != userId) {
            throw new UnauthorizedResourceAccessException();
        }
    }
}
