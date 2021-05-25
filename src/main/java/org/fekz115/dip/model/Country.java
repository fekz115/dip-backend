package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue
    private int id;
    private String name;
}
