package org.example.ide2markingapi.feature.media.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
//use
public record MediaResponse(
        String name,//unique
        String contentType,//PNC,JPEG,MP4
        String extension,
        String uri,//http

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long size
) {
}
