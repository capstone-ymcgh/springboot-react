package com.capstone.capstone_project.dto.response.boardfile;


/*
 * 파일 업로드 후 응답 DTO
 */

import com.capstone.capstone_project.entity.BoardFileEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecBoardFileUploadDTO {

    private Long fileId;
    private String originFileName;
    private String filePath;
    private String fileType;

    @Builder
    public RecBoardFileUploadDTO(Long fileId, String originFileName, String filePath, String fileType) {
        this.fileId = fileId;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public static RecBoardFileUploadDTO fromEntity(BoardFileEntity file) {
        return RecBoardFileUploadDTO.builder()
                .fileId(file.getId())
                .originFileName(file.getOriginFileName())
                .filePath(file.getFilePath())
                .fileType(file.getFileType())
                .build();
    }

}
