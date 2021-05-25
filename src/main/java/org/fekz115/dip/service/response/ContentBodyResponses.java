package org.fekz115.dip.service.response;

import lombok.Data;
import org.fekz115.dip.model.ContentBody;

public class ContentBodyResponses {

    @Data
    public static class CreateContentBodyResponse {
        private final ContentBody contentBody;
    }
}
