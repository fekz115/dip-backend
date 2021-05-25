package org.fekz115.dip.controller;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.ArticleService;
import org.fekz115.dip.service.UserService;
import org.fekz115.dip.service.exception.UserNotFound;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.fekz115.dip.service.request.ArticleServiceRequests.*;
import static org.fekz115.dip.service.response.ArticleServiceResponses.*;

@RestController
@RequestMapping("/api/article")
@AllArgsConstructor
public class ArticleController {

    private final UserService userService;
    private final ArticleService articleService;

    @PostMapping("/create")
    CreateArticleResponse create(@RequestBody CreateArticleRequest request, Principal principal) throws UserNotFound {
        request.setAuthor(getUserFromPrincipal(principal));
        return articleService.createArticle(request);
    }

    @PostMapping("{id}/rating")
    ChangeArticleRatingResponse changeArticleRating(@RequestBody ChangeArticleRatingRequest request, @PathVariable int id, Principal principal) throws UserNotFound {
        request.setUser(getUserFromPrincipal(principal));
        request.setArticleId(id);
        return articleService.changeArticleRating(request);
    }

    @GetMapping("{id}")
    FindArticleByIdResponse findArticleById(@PathVariable int id, Principal principal) {
        User user = null;
        try { user  = getUserFromPrincipal(principal); } catch (UserNotFound ignored) {}
        return articleService.findArticleById(new FindArticleByIdRequest(id, user));
    }

    private User getUserFromPrincipal(Principal principal) throws UserNotFound {
        return userService.getUserInfo(principal.getName());
    }
}
