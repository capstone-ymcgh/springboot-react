package com.capstone.capstone_project.dto.response.recipeboard;


import com.capstone.capstone_project.entity.RecBoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 *  list 요청에 대한 값 반환, 양방향 관계로 인해 JSON직렬화가 반복되는 문제를 해결하기 위한 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class RecBoardListDTO {

    // 작성일, 수정일, 작성자, 댓글 개수만 전체 목록에 대한 데이터로 받으면 됨
    // 상세한 댓글 내용 등은 상세보기에서 처리한다
    private Long boardId;
    private String title;
    private String content;
    private int viewCount;
    private String createdDate;
    private String modifiedDate;
    private String writerName;

    @Builder
    public RecBoardListDTO(Long boardId, String title, String content, int viewCount, String createdDate, String modifiedDate, String writerName) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.writerName = writerName;
    }

    // Entity -> DTO
    public static RecBoardListDTO fromEntity(RecBoardEntity board) {
        return RecBoardListDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .viewCount(board.getViewCount())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .writerName(board.getMember().getUsername())
                .build();
    }

}
