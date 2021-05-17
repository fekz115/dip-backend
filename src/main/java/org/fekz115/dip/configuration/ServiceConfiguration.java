package org.fekz115.dip.configuration;

import org.fekz115.dip.repository.UserRepository;
import org.fekz115.dip.service.UserNotificationService;
import org.fekz115.dip.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ServiceConfiguration {

    @Bean
    UserService userService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserNotificationService mailService
    ) {
        return new UserService(
                userRepository,
                passwordEncoder::encode,
                passwordEncoder::matches,
                () -> String.valueOf((int)(Math.random() * 5)),
                mailService
        );
    }

}
