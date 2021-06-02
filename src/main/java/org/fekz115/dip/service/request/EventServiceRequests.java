package org.fekz115.dip.service.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.fekz115.dip.service.request.dto.EventLocationDto;

import java.util.List;
import java.util.Optional;

public class EventServiceRequests {

    @Data
    public static class CreateEventRequest {
        private final int articleId;
        private final List<EventLocationDto> locations;
    }

    @Data
    @AllArgsConstructor
    public static class UpdateEventRequest {
        @JsonIgnore
        private int eventId;
        private final Integer articleId;
        private final List<EventLocationDto> locations;


        public Optional<Integer> getArticleId() {
            return Optional.ofNullable(articleId);
        }

        public Optional<List<EventLocationDto>> getLocations() {
            return Optional.ofNullable(locations);
        }
    }

    @AllArgsConstructor
    @Data
    public static class RemoveEventRequest {
        private final int eventId;
    }

}
