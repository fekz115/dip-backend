package org.fekz115.dip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Music implements Content {
    @Id
    private int id;
    private String name;
    @JsonIgnore
    private String fileName;
}
