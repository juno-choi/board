package com.juno.simple.board.controller;

import com.juno.simple.board.domain.dto.BoardPostRequest;
import com.juno.simple.board.domain.dto.BoardPutRequest;
import com.juno.simple.board.domain.response.BoardListResponse;
import com.juno.simple.board.domain.response.BoardResponse;
import com.juno.simple.board.service.BoardService;
import com.juno.simple.global.api.Response;
import com.juno.simple.global.api.ResponseEnums;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@Tag(name = "Board API", description = "Board API Documentation")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    @Operation(summary = "게시글 등록", description = "게시글 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    public ResponseEntity<Response<BoardResponse>> post(@RequestBody @Validated BoardPostRequest boardPostRequest, BindingResult bindingResult) {
        BoardResponse response = boardService.postBoard(boardPostRequest);
        return ResponseEntity.ok(
                Response.<BoardResponse>builder()
                        .code(ResponseEnums.SUCCESS.code)
                        .message(ResponseEnums.SUCCESS.message)
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/list")
    @Operation(summary = "게시글 전체 조회", description = "게시글 전체 조회 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    public ResponseEntity<Response<BoardListResponse>> getList(@RequestParam(name = "page", defaultValue = "0") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size,
                                                               @RequestParam(name = "sort", defaultValue = "DESC") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sort), "createdAt"));
        BoardListResponse response = boardService.getBoardList(pageable);
        return ResponseEntity.ok(
                Response.<BoardListResponse>builder()
                        .code(ResponseEnums.SUCCESS.code)
                        .message(ResponseEnums.SUCCESS.message)
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{board_id}")
    @Operation(summary = "게시글 상세 조회", description = "게시글 상세 조회 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    public ResponseEntity<Response<BoardResponse>> get(@PathVariable(name = "board_id")
                                                       @Schema(description = "게시글 번호", example = "1")
                                                       @Validated
                                                       @NotNull
                                                       Long boardId) {
        BoardResponse response = boardService.getBoard(boardId);
        return ResponseEntity.ok(
                Response.<BoardResponse>builder()
                        .code(ResponseEnums.SUCCESS.code)
                        .message(ResponseEnums.SUCCESS.message)
                        .data(response)
                        .build()
        );
    }

    @PutMapping("")
    @Operation(summary = "게시글 수정", description = "게시글 수정 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    public ResponseEntity<Response<BoardResponse>> patch(@RequestBody @Validated BoardPutRequest boardPutRequest, BindingResult bindingResult) {
        BoardResponse response = boardService.putBoard(boardPutRequest);
        return ResponseEntity.ok(
                Response.<BoardResponse>builder()
                        .code(ResponseEnums.SUCCESS.code)
                        .message(ResponseEnums.SUCCESS.message)
                        .data(response)
                        .build()
        );
    }
}
