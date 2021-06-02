package org.fekz115.dip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventLocation {

    @Id
    @GeneratedValue
    private int id;
    LocalDateTime startDate;
    LocalDateTime finishDate;
    @ManyToOne(cascade = CascadeType.ALL)
    Address address;

}
