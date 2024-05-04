package com.juno.simple.board.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juno.simple.board.domain.BoardEntity;
import lombok.Builder;

@Builder
public record BoardResponse(
    @JsonProperty("board_id") Long boardId
) {
    public static BoardResponse from(BoardEntity saveBoard) {
        return BoardResponse.builder()
                .boardId(saveBoard.getBoardId())
                .build();
    }
}
