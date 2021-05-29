package org.fekz115.dip.service.response.dto;

import lombok.Data;
import org.fekz115.dip.model.*;
import org.fekz115.dip.util.DtoUtils;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArticleDto {
    public ArticleDto(Article article, User user) {
        id = article.getId();
        title = article.getTitle();
        tags = article.getTags();
        publicationDate = article.getPublicationDate();
        body = article.getBody();
        canBeRated = article.isCanBeRated();
        canBeCommented = article.isCanBeCommented();
        showAuthor = article.isShowAuthor();
        author = new UserMinimal(article.getAuthor());
        likes = article.getLikes().size();
        dislikes = article.getDislikes().size();
        ratingState = DtoUtils.getRatingState(user, article.getLikes(), article.getDislikes());
    }

    private final int id;
    private final String title;
    private final Set<Tag> tags;
    private final LocalDateTime publicationDate;
    private final ContentBody body;
    private final boolean canBeRated;
    private final boolean canBeCommented;
    private final boolean showAuthor;
    private final UserMinimal author;
    private final int likes;
    private final int dislikes;
    private final RatingState ratingState;
}
