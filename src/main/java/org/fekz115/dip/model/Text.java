package org.fekz115.dip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Text implements Content {
    @Id
    @GeneratedValue
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
