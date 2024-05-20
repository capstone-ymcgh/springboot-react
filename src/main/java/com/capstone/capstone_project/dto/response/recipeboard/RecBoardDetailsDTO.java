package com.capstone.capstone_project.dto.response.recipeboard;



import com.capstone.capstone_project.dto.response.boardfile.RecBoardDetailsFileDTO;
import com.capstone.capstone_project.dto.response.recboardcomment.RecResBoardCommentDTO;
import com.capstone.capstone_project.entity.RecBoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
/*
 * 게시글 상세, 수정 요청에 대한 값 반환
 */
@Getter
@Setter
@NoArgsConstructor
public class RecBoardDetailsDTO {

    //  게시글 db
    private Long boardId;
    private String title;
    private String content;
    private int viewCount;
    private String writerName;
    private String createdDate;
    private String modifiedDate;

    // comments
    private List<RecResBoardCommentDTO> comments;

    // file
    private List<RecBoardDetailsFileDTO> files;

    @Builder
    public RecBoardDetailsDTO(Long boardId, String title, String content, int viewCount, String writerName, String createdDate, String modifiedDate, List<RecResBoardCommentDTO> comments, List<RecBoardDetailsFileDTO> files) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.writerName = writerName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.comments = comments;
        this.files = files;
    }

    public static RecBoardDetailsDTO fromEntity(RecBoardEntity board) {
        return RecBoardDetailsDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .writerName(board.getMember().getUsername())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .comments(board.getComments().stream()
                        .map(RecResBoardCommentDTO::fromEntity)
                        .collect(Collectors.toList()))
                .files(board.getFiles().stream()
                        .map(RecBoardDetailsFileDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}
