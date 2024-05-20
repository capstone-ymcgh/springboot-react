package com.capstone.capstone_project.controller;


import com.capstone.capstone_project.dto.request.recboardcomment.RecBoardCommentDTO;
import com.capstone.capstone_project.dto.response.recboardcomment.RecResBoardCommentDTO;
import com.capstone.capstone_project.entity.MemberEntity;
import com.capstone.capstone_project.service.RecBoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/{boardId}/comment")
@RequiredArgsConstructor
public class RecipeBoardCommentController {

    private final RecBoardCommentService recBoardCommentService;

    @GetMapping("/list")
    public ResponseEntity<Page<RecResBoardCommentDTO>> commentList(
            @PathVariable Long boardId,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<RecResBoardCommentDTO> commentList = recBoardCommentService.getAllComments(pageable, boardId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @PostMapping("/write")
    public ResponseEntity<RecResBoardCommentDTO> write(
            @AuthenticationPrincipal MemberEntity member,
            @PathVariable Long boardId,
            @RequestBody RecBoardCommentDTO commentDto) {

        RecResBoardCommentDTO saveCommentDTO = recBoardCommentService.write(boardId, member, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCommentDTO);
    }

    @PatchMapping("/update/{commentId}")
    public ResponseEntity<RecResBoardCommentDTO> update(
            @PathVariable Long commentId,
            @RequestBody RecBoardCommentDTO commentDto) {

        RecResBoardCommentDTO updateCommentDTO = recBoardCommentService.update(commentId, commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateCommentDTO);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Long> delete(@PathVariable Long commentId) {

        recBoardCommentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
