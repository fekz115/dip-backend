package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Location {
    @Id
    private int id;
    private double longitude;
    private double latitude;
}
