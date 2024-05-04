package com.juno.simple.board.service;

import com.juno.simple.board.domain.BoardEntity;
import com.juno.simple.board.domain.dto.BoardPostRequest;
import com.juno.simple.board.domain.response.BoardResponse;
import com.juno.simple.board.repository.BoardRepository;
import com.juno.simple.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BoardResponse postBoard (BoardPostRequest boardPostRequest) {
        memberRepository.findById(boardPostRequest.getMemberId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        BoardEntity saveBoard = boardRepository.save(boardPostRequest.toEntity());
        return BoardResponse.from(saveBoard);
    }
}
