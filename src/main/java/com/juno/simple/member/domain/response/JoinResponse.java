package com.juno.simple.member.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.juno.simple.member.domain.entity.MemberEntity;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record JoinResponse(
        @JsonProperty("member_id") Long memberId
) {
    public static JoinResponse from(MemberEntity saveMember) {
        return JoinResponse.builder()
                .memberId(saveMember.getMemberId())
                .build();
    }
}
