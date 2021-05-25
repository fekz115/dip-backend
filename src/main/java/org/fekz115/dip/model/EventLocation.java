package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class EventLocation {

    @Id
    @GeneratedValue
    private int id;
    LocalDateTime startDate;
    LocalDateTime finishDate;
    @ManyToOne
    Address address;

}
