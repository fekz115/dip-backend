package org.fekz115.dip.service.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.fekz115.dip.model.RatingState;
import org.fekz115.dip.model.User;
import org.fekz115.dip.service.request.dto.ContentDto;

import java.time.LocalDateTime;
import java.util.List;

public class CommentServiceRequests {

    @Data
    public static class CreateCommentRequest {
        @JsonIgnore
        private int articleId;
        @JsonIgnore
        private User author;
        private int replyToId;
        private LocalDateTime publicationDate;
        private List<ContentDto> content;
    }

    @Data
    public static class ChangeCommentRatingRequest {
        @JsonIgnore
        private User user;
        @JsonIgnore
        private int commentId;
        private RatingState newRatingState;
    }

    @Data
    public static class UpdateCommentRequest {
        @JsonIgnore
        private int id;
        @JsonIgnore
        private User user;
        private List<ContentDto> content;
    }

    @Data
    public static class RemoveCommentRequest {
        @JsonIgnore
        private int id;
    }
}
