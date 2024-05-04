package com.juno.simple.board.domain.dto;

import com.juno.simple.board.domain.BoardEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardPostRequest {
    @Schema(description = "회원번호", example = "1")
    @NotNull(message = "회원번호는 비어있을 수 없습니다.")
    private Long memberId;
    @Schema(description = "제목", example = "심플 게시판")
    @NotNull(message = "제목은 비어있을 수 없습니다.")
    private String title;
    @Schema(description = "내용", example = "게시판 내용입니다.")
    @NotNull(message = "내용은 비어있을 수 없습니다.")
    private String content;

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .memberId(memberId)
                .title(title)
                .content(content)
                .build();
    }
}
