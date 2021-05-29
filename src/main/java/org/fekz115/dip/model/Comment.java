package org.fekz115.dip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Article article;
    @ManyToOne
    private User user;
    @OneToOne
    private ContentBody contentBody;
    @OneToOne
    private Comment replyTo;
    private LocalDateTime publicationDate;
    @ManyToMany
    private Set<User> likes;
    @ManyToMany
    private Set<User> dislikes;
}
