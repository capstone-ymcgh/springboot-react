package com.capstone.capstone_project.dto.response.recboardcomment;

/*
 * 댓글 등록, 수정 값 반환
 */

import com.capstone.capstone_project.entity.RecBoardCommentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecResBoardCommentDTO {

    private Long commentId;
    private String content;
    private String createdDate;
    private String modifiedDate;
    private String commentWriterName; // 댓글 작성자

    @Builder
    public RecResBoardCommentDTO(Long commentId, String content, String createdDate, String modifiedDate, String commentWriterName) {
        this.commentId = commentId;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.commentWriterName = commentWriterName;
    }

    public static RecResBoardCommentDTO fromEntity(RecBoardCommentEntity comment) {
        return RecResBoardCommentDTO.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdDate(comment.getCreatedDate())
                .modifiedDate(comment.getModifiedDate())
                .commentWriterName(comment.getMember().getUsername())
                .build();
    }

}
