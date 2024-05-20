package com.capstone.capstone_project.dto.response.boardfile;


/*
 *  게시글 상세 정보에 포함될 file 정보 DTO
 */

import com.capstone.capstone_project.entity.BoardFileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecBoardDetailsFileDTO {

    private Long fileId;
    private String originFileName;
    private String fileType;

    @Builder
    public RecBoardDetailsFileDTO(Long fileId, String originFileName, String fileType) {
        this.fileId = fileId;
        this.originFileName = originFileName;
        this.fileType = fileType;
    }

    public static RecBoardDetailsFileDTO fromEntity(BoardFileEntity file) {
        return RecBoardDetailsFileDTO.builder()
                .fileId(file.getId())
                .originFileName(file.getOriginFileName())
                .fileType(file.getFileType())
                .build();
    }

}
