package org.fekz115.dip.service;

import lombok.AllArgsConstructor;
import org.fekz115.dip.model.Comment;
import org.fekz115.dip.repository.ArticleRepository;
import org.fekz115.dip.repository.CommentRepository;
import org.fekz115.dip.service.request.ContentBodyServiceRequests.CreateContentBodyRequest;
import org.fekz115.dip.service.response.dto.CommentDto;

import java.util.HashSet;

import static org.fekz115.dip.service.request.CommentServiceRequests.*;
import static org.fekz115.dip.service.response.CommentServiceResponses.*;

@AllArgsConstructor
public class CommentService {

    private final ContentBodyService contentBodyService;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CreateCommentResponse createComment(CreateCommentRequest request) {
        return new CreateCommentResponse(
                new CommentDto(
                        commentRepository.save(
                                Comment
                                        .builder()
                                        .article(articleRepository.findById(request.getArticleId()).orElseThrow())
                                        .contentBody(contentBodyService.createContentBody(new CreateContentBodyRequest(request.getContent())).getContentBody())
                                        .replyTo(commentRepository.findById(request.getReplyToId()).orElse(null))
                                        .user(request.getAuthor())
                                        .publicationDate(request.getPublicationDate())
                                        .dislikes(new HashSet<>())
                                        .likes(new HashSet<>())
                                        .build()
                        ),
                        request.getAuthor()
                )
        );
    }

    public ChangeCommentRatingResponse changeCommentRating(ChangeCommentRatingRequest request) {
        var comment = commentRepository.findById(request.getCommentId()).orElseThrow();
        comment.getLikes().remove(request.getUser());
        comment.getDislikes().remove(request.getUser());
        switch (request.getNewRatingState()) {
            case LIKED:
                comment.getLikes().add(request.getUser());
                break;
            case DISLIKED:
                comment.getDislikes().add(request.getUser());
                break;
        }
        return new ChangeCommentRatingResponse(
                new CommentDto(
                        commentRepository.save(comment),
                        request.getUser()
                )
        );
    }

    public UpdateCommentResponse updateComment(UpdateCommentRequest request) {
        var comment = commentRepository.findById(request.getId()).orElseThrow();
        comment.setContentBody(
                contentBodyService.createContentBody(
                        new CreateContentBodyRequest(
                                request.getContent()
                        )
                ).getContentBody()
        );
        return new UpdateCommentResponse(
                new CommentDto(
                        commentRepository.save(comment),
                        request.getUser()
                )
        );
    }

    public void removeComment(RemoveCommentRequest request) {
        commentRepository.deleteById(request.getId());
    }

}
