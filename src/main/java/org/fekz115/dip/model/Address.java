package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
public class Address {
    @Id
    private int id;
    private String building;
    @ManyToOne
    private Street street;
    @OneToOne
    private Location location;
}
