package com.capstone.capstone_project.entity;


import com.capstone.capstone_project.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 파일 정보를 저장하는 엔티티 클래스
 */

@Entity
@Table(name = "FILE")
@Getter
@NoArgsConstructor
public class BoardFileEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "FILE_ID")
    private Long id;

    @Column(name = "ORIGIN_FILE_NAME")
    private String originFileName;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "FILE_PATH")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    public RecBoardEntity board;

    @Builder
    public BoardFileEntity(Long id, String originFileName, String filePath, String fileType) {
        this.id = id;
        this.originFileName = originFileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public void setMappingBoard(RecBoardEntity board) {
        this.board = board;
    }

}
