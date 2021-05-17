package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class EventLocation {

    @Id
    private int id;
    LocalDateTime startDate;
    LocalDateTime finishDate;
    @ManyToOne
    Address address;

}
