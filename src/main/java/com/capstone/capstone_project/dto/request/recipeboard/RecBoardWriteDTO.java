package com.capstone.capstone_project.dto.request.recipeboard;


/*
 * 게시글 등록 요청
 */

import com.capstone.capstone_project.entity.RecBoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecBoardWriteDTO {
    private String title;
    private String content;

    public RecBoardWriteDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public static RecBoardEntity ofEntity(RecBoardWriteDTO dto) {
        return RecBoardEntity.builder()
                .title(dto.title)
                .content(dto.content)
                .build();
    }
}
