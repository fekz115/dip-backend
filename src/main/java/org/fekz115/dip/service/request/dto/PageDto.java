package org.fekz115.dip.service.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDto {
    private int page;
    private int pageSize;
}
