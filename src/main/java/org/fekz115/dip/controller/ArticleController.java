package org.fekz115.dip.controller;

import lombok.AllArgsConstructor;
import org.fekz115.dip.service.ArticleService;
import org.fekz115.dip.service.UserService;
import org.fekz115.dip.service.exception.UserNotFound;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        request.setAuthor(userService.getUserInfo(principal.getName()));
        return articleService.createArticle(request);
    }

}
