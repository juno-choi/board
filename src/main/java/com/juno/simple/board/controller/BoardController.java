package com.juno.simple.board.controller;

import com.juno.simple.board.domain.dto.BoardPostRequest;
import com.juno.simple.board.domain.response.BoardResponse;
import com.juno.simple.board.service.BoardService;
import com.juno.simple.global.api.Response;
import com.juno.simple.global.api.ResponseEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("")
    public ResponseEntity<Response<?>> post(@RequestBody @Validated BoardPostRequest boardPostRequest, BindingResult bindingResult) {
        BoardResponse response = boardService.postBoard(boardPostRequest);
        return ResponseEntity.ok(
                Response.<BoardResponse>builder()
                        .code(ResponseEnums.SUCCESS.code)
                        .message(ResponseEnums.SUCCESS.message)
                        .data(response)
                        .build()
        );
    }
}
