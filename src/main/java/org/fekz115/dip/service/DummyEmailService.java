package org.fekz115.dip.service;

import org.fekz115.dip.model.User;
import org.springframework.stereotype.Service;

@Service
public class DummyEmailService implements UserNotificationService {
    @Override
    public void notifyUserActivationCode(User user) {
        System.out.println("User " + user.getLogin() + " activation code: " + user.getActivationCode());
    }
}
