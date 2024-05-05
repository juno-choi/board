package com.juno.simple.board.domain;

import com.juno.simple.board.domain.dto.BoardPutRequest;
import com.juno.simple.member.domain.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOARD")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private String title;
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }
    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }

    public void put(BoardPutRequest boardPutRequest) {
        this.title = boardPutRequest.getTitle();
        this.content = boardPutRequest.getContent();
        this.modifiedAt = LocalDateTime.now();
    }
}
