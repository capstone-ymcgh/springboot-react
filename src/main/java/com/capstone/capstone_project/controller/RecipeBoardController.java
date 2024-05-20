package com.capstone.capstone_project.controller;


import com.capstone.capstone_project.dto.request.recipeboard.RecBoardUpdateDTO;
import com.capstone.capstone_project.dto.request.recipeboard.RecSearchData;
import com.capstone.capstone_project.dto.request.recipeboard.RecBoardWriteDTO;
import com.capstone.capstone_project.dto.response.recipeboard.RecBoardDetailsDTO;
import com.capstone.capstone_project.dto.response.recipeboard.RecBoardListDTO;
import com.capstone.capstone_project.dto.response.recipeboard.RecResBoardWriteDTO;
import com.capstone.capstone_project.entity.MemberEntity;
import com.capstone.capstone_project.service.RecBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class RecipeBoardController {
    private final RecBoardService recBoardService;

    // 페이징 목록
    @GetMapping("/list")
    public ResponseEntity<Page<RecBoardListDTO>> boardList(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<RecBoardListDTO> listDTO = recBoardService.getAllBoards(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    // 페이징 검색 , Get 요청 @RequestBody 사용할 수 없음
    @GetMapping("/search")
    public ResponseEntity<Page<RecBoardListDTO>> search(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String writerName) {
        RecSearchData searchData = RecSearchData.createdSearchData(title, content, writerName);
        Page<RecBoardListDTO> searchBoard = recBoardService.search(searchData, pageable);
        return  ResponseEntity.status(HttpStatus.OK).body(searchBoard);
    }

    @PostMapping("/write")
    public ResponseEntity<RecResBoardWriteDTO> write(
            @RequestBody RecBoardWriteDTO boardDTO,
            @AuthenticationPrincipal MemberEntity member) {
        Thread currentThread = Thread.currentThread();
        log.info("현재 실행 중인 스레드: " + currentThread.getName());
        RecResBoardWriteDTO saveBoardDTO = recBoardService.write(boardDTO, member);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveBoardDTO);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<RecBoardDetailsDTO> detail(@PathVariable("boardId") Long boardId) {
        RecBoardDetailsDTO findBoardDTO = recBoardService.detail(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(findBoardDTO);
    }

    // 상세보기 -> 수정
    @PatchMapping("/{boardId}/update")
    public ResponseEntity<RecBoardDetailsDTO> update(
            @PathVariable Long boardId,
            @RequestBody RecBoardUpdateDTO boardDTO) {
        RecBoardDetailsDTO updateBoardDTO = recBoardService.update(boardId, boardDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateBoardDTO);
    }

    // 상세보기 -> 삭제
    @DeleteMapping("/{boardId}/delete")
    public ResponseEntity<Long> delete(@PathVariable Long boardId) {
        recBoardService.delete(boardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
