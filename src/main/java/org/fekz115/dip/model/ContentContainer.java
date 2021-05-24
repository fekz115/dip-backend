package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ContentContainer {
    @Id
    private int id;
    private int index;
    @ManyToOne
    private Text text;
    @ManyToOne
    private Music music;
    @ManyToOne
    private Picture picture;
    @ManyToOne
    private Video video;
}
