package com.juno.simple.member.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.juno.simple.member.domain.entity.MemberEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record LoginResponse (
        @JsonProperty("member_id") Long memberId,
        String email,
        String name,
        @JsonProperty("created_at") LocalDateTime createdAt,
        @JsonProperty("modified_at") LocalDateTime modifiedAt
){
    public static LoginResponse from(MemberEntity findMember) {
        return LoginResponse.builder()
                .memberId(findMember.getMemberId())
                .email(findMember.getEmail())
                .name(findMember.getName())
                .createdAt(findMember.getCreatedAt())
                .modifiedAt(findMember.getModifiedAt())
                .build();
    }
}
