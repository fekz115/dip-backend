package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
public class EventDto {
    @Id
    private int id;
    @OneToOne
    private ArticleDto article;
    @OneToMany
    private List<EventLocation> locations;
}
