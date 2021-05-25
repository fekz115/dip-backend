package org.fekz115.dip.controller;

import org.fekz115.dip.service.ArticleService;
import org.fekz115.dip.service.UserService;
import org.fekz115.dip.service.exception.UserNotFound;
import org.fekz115.dip.service.request.dto.PageDto;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.fekz115.dip.service.request.ArticleServiceRequests.*;
import static org.fekz115.dip.service.response.ArticleServiceResponses.*;

@RestController
@RequestMapping("/api/article")
public class ArticleController extends CommonController {

    private final ArticleService articleService;

    public ArticleController(
            UserService userService,
            ArticleService articleService
    ) {
        super(userService);
        this.articleService = articleService;
    }

    @PostMapping("/create")
    CreateArticleResponse create(
            @RequestBody CreateArticleRequest request,
            Principal principal
    ) throws UserNotFound {
        request.setAuthor(getUserFromPrincipal(principal));
        return articleService.createArticle(request);
    }

    @PostMapping("{id}/rating")
    ChangeArticleRatingResponse changeArticleRating(
            @PathVariable int id,
            @RequestBody ChangeArticleRatingRequest request,
            Principal principal
    ) throws UserNotFound {
        request.setUser(getUserFromPrincipal(principal));
        request.setArticleId(id);
        return articleService.changeArticleRating(request);
    }

    @GetMapping("{id}")
    FindArticleByIdResponse findArticleById(
            @PathVariable int id,
            Principal principal
    ) {
        return articleService.findArticleById(
                new FindArticleByIdRequest(
                        id,
                        tryGetUserFromPrincipal(principal)
                )
        );
    }

    @PatchMapping("{id}")
    UpdateArticleResponse updateArticle(
            @PathVariable int id,
            @RequestBody UpdateArticleRequest request,
            Principal principal
    ) throws UserNotFound {
        request.setUser(getUserFromPrincipal(principal));
        request.setId(id);
        return articleService.updateArticle(request);
    }

    @DeleteMapping("{id}")
    void removeArticle(@PathVariable int id) {
        articleService.removeArticle(
                new RemoveArticleRequest(id)
        );
    }

    @GetMapping
    FindArticlesResponse findArticles(
            @RequestParam int pageSize,
            @RequestParam int page,
            Principal principal
    ) {
        return articleService.findArticles(
                new FindArticlesRequest(
                        new PageDto(page, pageSize),
                        tryGetUserFromPrincipal(principal)
                )
        );
    }
}
