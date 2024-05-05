package com.juno.simple.board.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BoardListResponse (
        @JsonProperty("total_page") int totalPage,
        @JsonProperty("total_elements") Long totalElements,
        @JsonProperty("number_of_elements") int numberOfElements,
        Boolean last,
        Boolean empty,
        List<BoardResponse> list
){}
