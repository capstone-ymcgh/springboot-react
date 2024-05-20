package com.capstone.capstone_project.entity;


import com.capstone.capstone_project.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 게시글 댓글을 나타내는 entity
 */

@Entity
@Getter
@NoArgsConstructor
public class RecBoardCommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    public MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    public RecBoardEntity board;

    @Builder
    public RecBoardCommentEntity(Long id, String content, MemberEntity member, RecBoardEntity board) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.board = board;
    }

    // Board와의 다대일(N:1) 관계를 설정하는 메소드
    public void setBoard(RecBoardEntity board) {
        this.board = board;
        board.getComments().add(this); // Board 엔티티에도 Comment를 추가합니다.
    }

    // Member와의 다대일(N:1) 관계를 설정하는 메소드
    public void setMember(MemberEntity member) {
        this.member = member;
        member.getComments().add(this); // Member 엔티티에도 Comment를 추가합니다.
    }

    // update
    public void update(String content) {
        this.content = content;
    }

}
