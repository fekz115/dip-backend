package org.fekz115.dip.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class ContentBodyDto {
    @Id
    private int id;
    @OneToMany
    List<ContentContainerDto> content;
}
