package org.fekz115.dip.service.request.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.fekz115.dip.service.MediaType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        value = {
        @JsonSubTypes.Type(
                name = "media",
                value = ContentDto.MediaDto.class
        ),
        @JsonSubTypes.Type(
                name = "text",
                value = ContentDto.TextDto.class
        ),
})
public abstract class ContentDto {
    protected String type;

    @AllArgsConstructor
    @Getter
    public static class MediaDto extends ContentDto {
        private final int id;
        private final MediaType type;
    }

    @AllArgsConstructor
    @Getter
    public static class TextDto extends ContentDto {
        private final String text;
        private final String link;
        private final boolean strike;
        private final boolean bold;
        private final boolean italic;
        private final int size;
        private final int color;
        private final boolean separate;
    }
}
