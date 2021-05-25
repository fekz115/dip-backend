package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Street {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ManyToOne
    private City city;
}
