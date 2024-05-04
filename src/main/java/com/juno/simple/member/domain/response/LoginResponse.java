package com.juno.simple.member.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LoginResponse (
        @JsonProperty("member_id") Long memberId,
        String email,
        String name,
        @JsonProperty("created_at") LocalDateTime createdAt,
        @JsonProperty("modified_at") LocalDateTime modifiedAt
){}
