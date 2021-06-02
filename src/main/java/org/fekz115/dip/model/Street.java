package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Street {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private City city;
}
