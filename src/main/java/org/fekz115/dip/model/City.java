package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class City {
    @Id
    private int id;
    private String name;
    @ManyToOne
    private Country country;
}
