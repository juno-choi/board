package com.juno.simple.member.controller;

import com.juno.simple.member.domain.dto.JoinRequest;
import com.juno.simple.member.domain.response.JoinResponse;
import com.juno.simple.member.service.MemberService;
import com.juno.simple.global.api.Response;
import com.juno.simple.global.api.ResponseEnums;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@Tag(name = "Member API", description = "Member API Documentation")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/join")
    @Operation(summary = "회원가입", description = "회원가입 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    public ResponseEntity<Response<JoinResponse>> join (@RequestBody @Validated JoinRequest joinRequest, BindingResult bindingResult) {
        JoinResponse response = memberService.join(joinRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.<JoinResponse>builder()
                    .code(ResponseEnums.SUCCESS.code)
                    .message(ResponseEnums.SUCCESS.message)
                    .data(response)
                    .build()
        );
    }
}
