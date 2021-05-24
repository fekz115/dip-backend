package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Comment {
    @Id
    private int id;
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
