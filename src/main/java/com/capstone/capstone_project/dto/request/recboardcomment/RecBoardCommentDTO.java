package com.capstone.capstone_project.dto.request.recboardcomment;


/*
 * 댓글 등록, 수정 요청.
 * MemberEntity, Board는 URI Resource로 받는다
 */

import com.capstone.capstone_project.entity.RecBoardCommentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecBoardCommentDTO {
    private String content;

    @Builder
    public RecBoardCommentDTO(String content) {
        this.content = content;
    }

    public static RecBoardCommentEntity ofEntity(RecBoardCommentDTO dto) {
        return RecBoardCommentEntity.builder()
                .content(dto.getContent())
                .build();
    }
}
