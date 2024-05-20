package com.capstone.capstone_project.dto.request.recipeboard;


/*
 * 게시글 수정 요청, 작성자는 Authenication을 받음.
 */


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecBoardUpdateDTO {
    private String title;
    private String content;

    @Builder
    public RecBoardUpdateDTO(String title, String content, String category) {
        this.title = title;
        this.content = content;
    }
}
