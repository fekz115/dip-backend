package org.fekz115.dip.controller;

import org.fekz115.dip.service.CommentService;
import org.fekz115.dip.service.UserService;
import org.fekz115.dip.service.exception.UserNotFound;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.fekz115.dip.service.response.CommentServiceResponses.*;
import static org.fekz115.dip.service.request.CommentServiceRequests.*;

@RestController
public class CommentController extends CommonController {

    private final CommentService commentService;

    public CommentController(
            UserService userService,
            CommentService commentService
    ) {
        super(userService);
        this.commentService = commentService;
    }

    @PostMapping("/api/article/{articleId}/comments/create")
    CreateCommentResponse createComment(
            @PathVariable int articleId,
            @RequestBody CreateCommentRequest request,
            Principal principal
    ) throws UserNotFound {
        request.setArticleId(articleId);
        request.setAuthor(getUserFromPrincipal(principal));
        return commentService.createComment(request);
    }

    @PostMapping("/api/comment/{id}")
    ChangeCommentRatingResponse changeCommentRating(
            @PathVariable int id,
            @RequestParam ChangeCommentRatingRequest request,
            Principal principal
    ) throws UserNotFound {
        request.setCommentId(id);
        request.setUser(getUserFromPrincipal(principal));
        return commentService.changeCommentRating(request);
    }

    @PatchMapping("/api/comment/{id}")
    UpdateCommentResponse updateComment(
            @PathVariable int id,
            @RequestParam UpdateCommentRequest request,
            Principal principal
    ) throws UserNotFound {
        request.setId(id);
        request.setUser(getUserFromPrincipal(principal));
        return commentService.updateComment(request);
    }

    @DeleteMapping("/api/comment/{id}")
    void removeComment(
            @PathVariable int id,
            @RequestParam RemoveCommentRequest request
    ) {
        request.setId(id);
        commentService.removeComment(request);
    }

}
