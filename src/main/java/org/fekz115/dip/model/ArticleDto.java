package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class ArticleDto {
    @Id
    private int id;
    private String title;
    @OneToMany
    private Set<Tag> tags;
    private LocalDateTime publicationDate;
    @OneToOne
    private ContentBodyDto body;
    @OneToMany
    private List<CommentDto> comments;
    private boolean canBeRated;
    private boolean canBeCommented;
    private boolean showAuthor;
    @ManyToOne
    private User author;
    @ManyToMany
    private Set<User> likes;
    @ManyToMany
    private Set<User> dislikes;
}
