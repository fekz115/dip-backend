package org.fekz115.dip.service.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.fekz115.dip.model.RatingState;
import org.fekz115.dip.model.Tag;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.request.dto.ContentDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ArticleServiceRequests {

    @Data
    public static class CreateArticleRequest {
        private final String title;
        private final Set<Tag> tags;
        private final LocalDateTime publicationDate;
        private final List<ContentDto> content;
        private final boolean canBeRated;
        private final boolean canBeCommented;
        private final boolean showAuthor;
        @JsonIgnore
        private User author;
    }

    @Data
    public static class ChangeArticleRatingRequest {
        @JsonIgnore
        private int articleId;
        private RatingState newRatingState;
        @JsonIgnore
        private User user;
    }

    @Data
    @AllArgsConstructor
    public static class FindArticleByIdRequest {
        @JsonIgnore
        private int articleId;
        @JsonIgnore
        private User user;
    }

    @Data
    public static class UpdateArticleRequest {
        @JsonIgnore
        private int id;
        private final Optional<String> title;
        private final Optional<Set<Tag>> tags;
        private final Optional<LocalDateTime> publicationDate;
        private final Optional<List<ContentDto>> content;
        private final Optional<Boolean> canBeRated;
        private final Optional<Boolean> canBeCommented;
        private final Optional<Boolean> showAuthor;
        @JsonIgnore
        private User user;
    }
}
