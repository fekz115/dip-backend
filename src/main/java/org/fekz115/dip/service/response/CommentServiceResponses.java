package org.fekz115.dip.service.response;

import lombok.Data;
import org.fekz115.dip.service.response.dto.CommentDto;

public class CommentServiceResponses {

    @Data
    public static class CreateCommentResponse {
        private final CommentDto commentDto;
    }

    @Data
    public static class ChangeCommentRatingResponse {
        private final CommentDto commentDto;
    }

    @Data
    public static class UpdateCommentResponse {
        private final CommentDto commentDto;
    }
}
