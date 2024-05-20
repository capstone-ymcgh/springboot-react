package com.capstone.capstone_project.controller;


import com.capstone.capstone_project.dto.response.boardfile.RecBoardFileDownDTO;
import com.capstone.capstone_project.dto.response.boardfile.RecBoardFileUploadDTO;
import com.capstone.capstone_project.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/board/{boardId}/file")
@RequiredArgsConstructor
public class BoardFileController {

    private final BoardFileService boardFileService;

    @PostMapping("/upload")
    public ResponseEntity<List<RecBoardFileUploadDTO>> upload (
            @PathVariable Long boardId,
            @RequestParam("file") List<MultipartFile> files) throws IOException {
        List<RecBoardFileUploadDTO> saveFile = boardFileService.upload(boardId, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveFile);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download (
            @RequestParam("fileId") Long fileId) throws IOException {
        RecBoardFileDownDTO downloadDto = boardFileService.download(fileId);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(downloadDto.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + downloadDto.getFilename() + "\"")
                .body(new ByteArrayResource(downloadDto.getContent()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete (
            @RequestParam("fileId") Long fileId) {
        boardFileService.delete(fileId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
