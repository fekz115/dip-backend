package org.fekz115.dip.controller;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.ArticleService;
import org.fekz115.dip.service.UserService;
import org.fekz115.dip.service.exception.UserNotFound;
import org.fekz115.dip.service.request.dto.PageDto;
import org.springframework.data.domain.Pageable;
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

    @PatchMapping("{id}")
    UpdateArticleResponse updateArticle(@RequestBody UpdateArticleRequest request, Principal principal, @PathVariable int id) throws UserNotFound {
        request.setUser(getUserFromPrincipal(principal));
        request.setId(id);
        return articleService.updateArticle(request);
    }

    @GetMapping
    FindArticlesResponse findArticles(@RequestParam int pageSize, @RequestParam int page, Principal principal) {
        FindArticlesRequest request = new FindArticlesRequest(new PageDto(page, pageSize));
        try { request.setUser(getUserFromPrincipal(principal)); } catch (UserNotFound ignored) {}
        return articleService.findArticles(request);
    }

    private User getUserFromPrincipal(Principal principal) throws UserNotFound {
        return userService.getUserInfo(principal.getName());
    }
}
