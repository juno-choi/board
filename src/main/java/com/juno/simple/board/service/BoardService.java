package com.juno.simple.board.service;

import com.juno.simple.board.domain.BoardEntity;
import com.juno.simple.board.domain.dto.BoardPostRequest;
import com.juno.simple.board.domain.dto.BoardPutRequest;
import com.juno.simple.board.domain.response.BoardListResponse;
import com.juno.simple.board.domain.response.BoardResponse;
import com.juno.simple.board.repository.BoardRepository;
import com.juno.simple.config.security.TokenProvider;
import com.juno.simple.member.domain.entity.MemberEntity;
import com.juno.simple.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public BoardResponse postBoard (BoardPostRequest boardPostRequest, HttpServletRequest request) {
        Authentication authorization = tokenProvider.getAuthentication(request.getHeader("Authorization"));
        Long memberId = Long.parseLong(authorization.getName());
        MemberEntity findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        BoardEntity saveBoard = boardRepository.save(boardPostRequest.toEntity(findMember));
        return BoardResponse.from(saveBoard);
    }

    public BoardListResponse getBoardList(Pageable pageable) {
        Page<BoardEntity> page = boardRepository.findAll(pageable);
        List<BoardResponse> list = page.getContent().stream().map(BoardResponse::from).collect(Collectors.toList());

        return BoardListResponse.builder()
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .last(page.isLast())
                .empty(page.isEmpty())
                .list(list)
                .build();
    }

    public BoardResponse getBoard(Long boardId) {
        BoardEntity findBoard = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("등록되지 않은 게시글입니다."));
        return BoardResponse.from(findBoard);
    }

    @Transactional
    public BoardResponse putBoard(BoardPutRequest boardPutRequest) {
        BoardEntity findBoard = boardRepository.findById(boardPutRequest.getBoardId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는게시글입니다.")
        );

        if (!boardPutRequest.equalsMember(findBoard)) {
            throw new IllegalArgumentException("게시글 등록자만 수정 가능합니다.");
        }

        findBoard.put(boardPutRequest);
        return BoardResponse.from(findBoard);
    }
}
