package org.fekz115.dip.controller;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Article;
import org.fekz115.dip.model.Event;
import org.fekz115.dip.model.User;
import org.fekz115.dip.repository.ArticleRepository;
import org.fekz115.dip.repository.EventRepository;
import org.fekz115.dip.service.UserService;
import org.fekz115.dip.service.exception.ActivationCodeIncorrect;
import org.fekz115.dip.service.exception.LoginOrEmailAlreadyUsed;
import org.fekz115.dip.service.exception.LoginOrPasswordIncorrect;
import org.fekz115.dip.service.exception.UserNotFound;
import org.fekz115.dip.service.request.UserServiceRequests.*;
import org.fekz115.dip.service.response.UserServiceResponses.*;
import org.fekz115.dip.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final ArticleRepository articleRepository;
    private final EventRepository eventRepository;

    @PostMapping("/registration")
    public RegisterUserResponse registerUser(@RequestBody RegisterUserRequest request) throws LoginOrEmailAlreadyUsed {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserRequest request) throws LoginOrPasswordIncorrect {
        return jwtTokenUtil.generateToken(userService.login(request).getUser());
    }

    @PostMapping("/activate")
    public ActivateUserResponse activate(Principal principal, String activationCode) throws UserNotFound, ActivationCodeIncorrect {
        return userService.activate(new ActivateUserRequest(principal.getName(), activationCode));
    }

    @GetMapping("/me")
    public User getMe(Principal principal) throws UserNotFound {
        return userService.getUserInfo(principal.getName());
    }

    @GetMapping("/me/articles")
    public List<Article> getArticles(Principal principal) throws UserNotFound {
        return articleRepository.findByAuthor(getUserFromPrincipal(principal));
    }

    @GetMapping("/me/events")
    public List<Event> getEvents(Principal principal) throws UserNotFound {
        return eventRepository.findByArticleAuthor(getUserFromPrincipal(principal));
    }

    private User getUserFromPrincipal(Principal principal) throws UserNotFound {
        return userService.getUserInfo(principal.getName());
    }

}
