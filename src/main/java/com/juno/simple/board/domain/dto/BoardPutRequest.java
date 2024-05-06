package com.juno.simple.board.domain.dto;

import com.juno.simple.board.domain.BoardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardPutRequest {
    @Schema(description = "게시글 번호", example = "1")
    @NotNull(message = "게시글 번호는 비어있을 수 없습니다.")
    private Long boardId;
    @Schema(description = "수정 제목", example = "수정될 제목입니다.")
    @NotNull(message = "제목은 비어있을 수 없습니다.")
    private String title;
    @Schema(description = "수정 내용", example = "수정될 게시판 내용입니다.")
    @NotNull(message = "내용은 비어있을 수 없습니다.")
    private String content;

}
