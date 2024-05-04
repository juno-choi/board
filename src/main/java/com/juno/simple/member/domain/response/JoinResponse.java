package com.juno.simple.member.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juno.simple.member.domain.entity.MemberEntity;
import lombok.Builder;

@Builder
public record JoinResponse(
        @JsonProperty("member_id") Long memberId
) {
    public static JoinResponse from(MemberEntity saveMember) {
        return JoinResponse.builder()
                .memberId(saveMember.getMemberId())
                .build();
    }
}
