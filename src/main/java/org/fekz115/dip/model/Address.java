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
    @ManyToOne
    private Street street;
    @OneToOne
    private Location location;
}
