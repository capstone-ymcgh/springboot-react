package com.capstone.capstone_project.dto.response.boardfile;


import com.capstone.capstone_project.entity.BoardFileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 파일 다운로드를 위한 DTO
 */

@Getter
@Setter
@NoArgsConstructor
public class RecBoardFileDownDTO {

    private String filename;
    private String fileType;
    private byte[] content;

    @Builder
    public RecBoardFileDownDTO(String filename, String fileType, byte[] content) {
        this.filename = filename;
        this.fileType = fileType;
        this.content = content;
    }

    public static RecBoardFileDownDTO fromFileResource(BoardFileEntity file, String contentType, byte[] content) {
        return RecBoardFileDownDTO.builder()
                .filename(file.getOriginFileName())
                .fileType(contentType)
                .content(content)
                .build();
    }

}
