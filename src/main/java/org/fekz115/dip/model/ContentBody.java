package org.fekz115.dip.model;

import lombok.Data;

import java.util.List;

@Data
public class ContentBody {
    private int id;
    List<ContentContainer> content;
}
