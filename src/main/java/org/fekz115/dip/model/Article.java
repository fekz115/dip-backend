package org.fekz115.dip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Tag> tags;
    private LocalDateTime publicationDate;
    @OneToOne
    private ContentBody body;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;
    private boolean canBeRated;
    private boolean canBeCommented;
    private boolean showAuthor;
    @ManyToOne
    private User author;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> likes;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> dislikes;
}
