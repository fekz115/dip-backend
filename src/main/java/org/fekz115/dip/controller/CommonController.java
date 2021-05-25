package org.fekz115.dip.controller;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.UserService;
import org.fekz115.dip.service.exception.UserNotFound;

import java.security.Principal;

@AllArgsConstructor
public abstract class CommonController {

    private final UserService userService;

    protected User getUserFromPrincipal(Principal principal) throws UserNotFound {
        return userService.getUserInfo(principal.getName());
    }

    protected User tryGetUserFromPrincipal(Principal principal) {
        try {
            return userService.getUserInfo(principal.getName());
        } catch (UserNotFound ignored) {
        }
        return null;
    }
}
