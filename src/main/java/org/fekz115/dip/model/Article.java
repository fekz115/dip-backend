package org.fekz115.dip.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class Article {
    private int id;
    private String title;
    private Set<Tag> tags;
    private LocalDateTime publicationDate;
    private ContentBody body;
    private List<Comment> comments;
    private boolean canBeRated;
    private boolean canBeCommented;
    private boolean showAuthor;
    private UserMinimal author;
    private RatingState ratingState;
}
