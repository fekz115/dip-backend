package org.fekz115.dip.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private int id;
    private UserMinimal author;
    private ContentBody contentBody;
    private Comment replyTo;
    private LocalDateTime publicationDate;
    private RatingState ratingState;
}
