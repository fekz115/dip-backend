package org.fekz115.dip.service.request.dto;

import lombok.Data;
import org.fekz115.dip.model.Address;

import java.time.LocalDateTime;

@Data
public class EventLocationDto {
    LocalDateTime startDate;
    LocalDateTime finishDate;
    Address address;
}
