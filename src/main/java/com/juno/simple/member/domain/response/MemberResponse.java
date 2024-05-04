package com.juno.simple.member.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juno.simple.member.domain.entity.MemberEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MemberResponse (
    @JsonProperty("member_id") Long memberId,
    String email,
    String name,
    @JsonProperty("created_at") LocalDateTime createdAt,
    @JsonProperty("modified_at") LocalDateTime modifiedAt
) {
    public static MemberResponse from(MemberEntity findMember) {
        return MemberResponse.builder()
                .memberId(findMember.getMemberId())
                .email(findMember.getEmail())
                .name(findMember.getName())
                .createdAt(findMember.getCreatedAt())
                .modifiedAt(findMember.getModifiedAt())
                .build();
    }
}
