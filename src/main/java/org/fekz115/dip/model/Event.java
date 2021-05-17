package org.fekz115.dip.model;

import lombok.Data;

import java.util.List;

@Data
public class Event {
    private int id;
    private Article article;
    private List<EventLocation> locations;
}
