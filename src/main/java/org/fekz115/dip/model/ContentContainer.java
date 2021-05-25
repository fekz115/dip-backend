package org.fekz115.dip.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentContainer {
    @Id
    @GeneratedValue
    private int id;
    private int index;
    @ManyToOne(cascade = CascadeType.ALL)
    private Text text;
    @ManyToOne(cascade = CascadeType.ALL)
    private Music music;
    @ManyToOne(cascade = CascadeType.ALL)
    private Picture picture;
    @ManyToOne(cascade = CascadeType.ALL)
    private Video video;
}
