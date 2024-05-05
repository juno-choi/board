package com.juno.simple.board.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.juno.simple.board.domain.BoardEntity;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BoardResponse(
    @JsonProperty("board_id") Long boardId,
    @JsonProperty("member_id") Long memberId,
    String title,
    String content,
    String name,
    @JsonProperty("created_at") LocalDateTime createdAt,
    @JsonProperty("modified_at") LocalDateTime modifiedAt
) {
    public static BoardResponse from(BoardEntity saveBoard) {
        return BoardResponse.builder()
                .boardId(saveBoard.getBoardId())
                .createdAt(saveBoard.getCreatedAt())
                .modifiedAt(saveBoard.getModifiedAt())
                .memberId(saveBoard.getMember().getMemberId())
                .title(saveBoard.getTitle())
                .content(saveBoard.getContent())
                .name(saveBoard.getMember().getName())
                .build();
    }
}
