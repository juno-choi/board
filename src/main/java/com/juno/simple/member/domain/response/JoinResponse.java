package com.juno.simple.member.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record JoinResponse(
        @JsonProperty("member_id") Long memberId
) {}
