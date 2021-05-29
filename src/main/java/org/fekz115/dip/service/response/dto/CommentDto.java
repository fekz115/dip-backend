package org.fekz115.dip.service.response.dto;

import lombok.Data;
import org.fekz115.dip.model.Comment;
import org.fekz115.dip.model.ContentBody;
import org.fekz115.dip.model.RatingState;
import org.fekz115.dip.model.User;
import org.fekz115.dip.util.DtoUtils;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private final int id;
    private final int articleId;
    private final UserMinimal author;
    private final ContentBody contentBody;
    private final int replyToId;
    private final LocalDateTime publicationDate;
    private final int likes;
    private final int dislikes;
    private final RatingState ratingState;

    public CommentDto(Comment comment, User user) {
        id = comment.getId();
        articleId = comment.getArticle().getId();
        author = new UserMinimal(comment.getUser());
        contentBody = comment.getContentBody();
        replyToId = comment.getReplyTo().getId();
        publicationDate = comment.getPublicationDate();
        likes = comment.getLikes().size();
        dislikes = comment.getDislikes().size();
        ratingState = DtoUtils.getRatingState(user, comment.getLikes(), comment.getDislikes());
    }
}
