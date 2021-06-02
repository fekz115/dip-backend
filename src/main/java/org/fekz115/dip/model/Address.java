package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String building;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Street street;
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;
}
