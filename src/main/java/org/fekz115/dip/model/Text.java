package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Text implements Content {
    @Id
    private int id;
    private String data;
    private String link;
    private boolean strike;
    private boolean bold;
    private boolean italic;
    private int size;
    private int color;
    private boolean separate;
}
