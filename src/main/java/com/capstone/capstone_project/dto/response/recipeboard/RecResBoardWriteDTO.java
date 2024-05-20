package com.capstone.capstone_project.dto.response.recipeboard;

/*
 * 게시글 등록 값 반환
 */

import com.capstone.capstone_project.entity.RecBoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecResBoardWriteDTO {

    private Long boardId;
    private String title;
    private String content;
    private String writerName;
    private String createdDate;

    @Builder
    public RecResBoardWriteDTO(Long boardId, String title, String content, String writerName, String createdDate) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.createdDate = createdDate;
    }

    public static RecResBoardWriteDTO fromEntity(RecBoardEntity board, String writerName) {
        return RecResBoardWriteDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writerName(writerName)
                .createdDate(board.getCreatedDate())
                .build();
    }

}
