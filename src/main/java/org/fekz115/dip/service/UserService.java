package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Role;
import org.fekz115.dip.model.User;
import org.fekz115.dip.model.UserInfo;
import org.fekz115.dip.repository.UserRepository;
import org.fekz115.dip.service.exception.ActivationCodeIncorrect;
import org.fekz115.dip.service.exception.LoginOrEmailAlreadyUsed;
import org.fekz115.dip.service.exception.LoginOrPasswordIncorrect;
import org.fekz115.dip.service.exception.UserNotFound;
import org.fekz115.dip.service.request.UserServiceRequests.*;
import org.fekz115.dip.service.response.UserServiceResponses.*;

import java.util.Collections;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Function<String, String> passwordEncoder;
    private final BiPredicate<String, String> passwordMatcher;
    private final Supplier<String> activationCodeSupplier;
    private final UserNotificationService mailService;

    public RegisterUserResponse registerUser(RegisterUserRequest request) throws LoginOrEmailAlreadyUsed {
        var list = userRepository.findByLoginOrEmail(request.getLogin(), request.getEmail());
        if (list.isEmpty()) {
            var unsaved = new User();
            unsaved.setActive(false);
            unsaved.setUserInfo(new UserInfo());
            unsaved.setBanned(false);
            unsaved.setEmail(request.getEmail());
            unsaved.setLogin(request.getLogin());
            unsaved.setRoles(Collections.singleton(Role.USER));
            unsaved.setActivationCode(activationCodeSupplier.get());
            unsaved.setPassword(passwordEncoder.apply(request.getPassword()));
            var user = userRepository.save(unsaved);
            mailService.notifyUserActivationCode(user);
            return new RegisterUserResponse(user);
        } else {
            throw new LoginOrEmailAlreadyUsed(new LoginOrEmailAlreadyUsed.LoginOrEmailAlreadyUsedFields(
                    list.stream().anyMatch(x -> x.getLogin().equals(request.getLogin())),
                    list.stream().anyMatch(x -> x.getEmail().equals(request.getEmail()))
            ));
        }
    }

    public LoginUserResponse login(LoginUserRequest request) throws LoginOrPasswordIncorrect {
        var user = userRepository.findByLogin(request.getLogin())
                .orElseThrow(() -> new LoginOrPasswordIncorrect(new LoginOrPasswordIncorrect.LoginOrPasswordIncorrectFields(false)));
        if (passwordMatcher.test(request.getPassword(), user.getPassword())) {
            return new LoginUserResponse(user);
        } else {
            throw new LoginOrPasswordIncorrect(new LoginOrPasswordIncorrect.LoginOrPasswordIncorrectFields(true));
        }
    }

    public ActivateUserResponse activate(ActivateUserRequest request) throws ActivationCodeIncorrect, UserNotFound {
        var user = userRepository
                .findByLogin(request.getLogin())
                .orElseThrow(UserNotFound::new);
        if(user.getActivationCode().equals(request.getActivationCode())) {
            user.setActive(true);
            user.setActivationCode(null);
            return new ActivateUserResponse(userRepository.save(user));
        } else {
            throw new ActivationCodeIncorrect(new ActivationCodeIncorrect.ActivationCodeIncorrectFields(request.getActivationCode()));
        }
    }

    public User getUserInfo(String login) throws UserNotFound {
        return userRepository.findByLogin(login).orElseThrow(UserNotFound::new);
    }

}
