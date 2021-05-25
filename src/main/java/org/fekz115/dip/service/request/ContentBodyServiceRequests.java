package org.fekz115.dip.service.request;

import lombok.Data;
import org.fekz115.dip.service.request.dto.ContentDto;

import java.util.List;

public class ContentBodyServiceRequests {

    @Data
    public static class CreateContentBodyRequest {
        private final List<ContentDto> content;
    }

}
