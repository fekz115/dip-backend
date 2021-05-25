package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Article article;
    @OneToMany
    private List<EventLocation> locations;
}
