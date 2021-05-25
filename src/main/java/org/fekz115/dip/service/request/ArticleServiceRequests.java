package org.fekz115.dip.service.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.fekz115.dip.model.Tag;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.request.dto.ContentDto;

import java.time.LocalDateTime;
import java.util.List;
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
}
