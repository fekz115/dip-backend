package org.fekz115.dip.service.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fekz115.dip.model.RatingState;
import org.fekz115.dip.model.Tag;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.request.dto.ContentDto;
import org.fekz115.dip.service.request.dto.PageDto;
import org.springframework.data.domain.Pageable;

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

    @Setter
    @NoArgsConstructor
    public static class UpdateArticleRequest {
        @JsonIgnore
        private int id;
        private String title;
        private Set<Tag> tags;
        private LocalDateTime publicationDate;
        private List<ContentDto> content;
        private Boolean canBeRated;
        private Boolean canBeCommented;
        private Boolean showAuthor;
        @JsonIgnore
        private User user;

        public int getId() {
            return this.id;
        }

        public Optional<String> getTitle() {
            return Optional.ofNullable(this.title);
        }

        public Optional<Set<Tag>> getTags() {
            return Optional.ofNullable(this.tags);
        }

        public Optional<LocalDateTime> getPublicationDate() {
            return Optional.ofNullable(this.publicationDate);
        }

        public Optional<List<ContentDto>> getContent() {
            return Optional.ofNullable(this.content);
        }

        public Optional<Boolean> getCanBeRated() {
            return Optional.ofNullable(this.canBeRated);
        }

        public Optional<Boolean> getCanBeCommented() {
            return Optional.ofNullable(this.canBeCommented);
        }

        public Optional<Boolean> getShowAuthor() {
            return Optional.ofNullable(this.showAuthor);
        }

        public User getUser() {
            return this.user;
        }

    }

    @Data
    public static class FindArticlesRequest {
        private final PageDto page;
        @JsonIgnore
        private User user;
    }

}
