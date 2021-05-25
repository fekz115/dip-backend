package org.fekz115.dip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Video implements Content {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @JsonIgnore
    private String fileName;
}
