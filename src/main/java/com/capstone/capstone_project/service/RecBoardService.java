package com.capstone.capstone_project.service;

import com.capstone.capstone_project.common.exception.ResourceNotFoundException;
import com.capstone.capstone_project.dto.request.recipeboard.RecBoardUpdateDTO;
import com.capstone.capstone_project.dto.request.recipeboard.RecBoardWriteDTO;
import com.capstone.capstone_project.dto.request.recipeboard.RecSearchData;
import com.capstone.capstone_project.dto.response.recipeboard.RecBoardDetailsDTO;
import com.capstone.capstone_project.dto.response.recipeboard.RecBoardListDTO;
import com.capstone.capstone_project.dto.response.recipeboard.RecResBoardWriteDTO;
import com.capstone.capstone_project.entity.MemberEntity;
import com.capstone.capstone_project.entity.RecBoardEntity;
import com.capstone.capstone_project.repository.MemberRepository;
import com.capstone.capstone_project.repository.RecBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecBoardService {

    private final RecBoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 페이징 리스트
    public Page<RecBoardListDTO> getAllBoards(Pageable pageable) {
        Page<RecBoardEntity> boards = boardRepository.findAllWithMemberAndComments(pageable);
        List<RecBoardListDTO> list = boards.getContent().stream()
                .map(RecBoardListDTO::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, boards.getTotalElements());
    }

    // 게시글 검색, isEmpty() == ""
    public Page<RecBoardListDTO> search(RecSearchData searchData, Pageable pageable) {
        Page<RecBoardEntity> result = null;
        if (!searchData.getTitle().isEmpty()) {
            result = boardRepository.findAllTitleContaining(searchData.getTitle(), pageable);
        } else if (!searchData.getContent().isEmpty()) {
            result = boardRepository.findAllContentContaining(searchData.getContent(), pageable);
        } else if (!searchData.getWriterName().isEmpty()) {
            result = boardRepository.findAllUsernameContaining(searchData.getWriterName(), pageable);
        }
        List<RecBoardListDTO> list = result.getContent().stream()
                .map(RecBoardListDTO::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(list, pageable, result.getTotalElements());
    }

    // 게시글 등록
    public RecResBoardWriteDTO write(RecBoardWriteDTO boardDTO, MemberEntity member) {

        RecBoardEntity board = RecBoardWriteDTO.ofEntity(boardDTO);
        MemberEntity writerMember = memberRepository.findByEmail(member.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Email", member.getEmail())
        );
        board.setMappingMember(writerMember);
        RecBoardEntity saveBoard = boardRepository.save(board);
        return RecResBoardWriteDTO.fromEntity(saveBoard, writerMember.getUsername());
    }

    // 게시글 상세보기
    public RecBoardDetailsDTO detail(Long boardId) {
        RecBoardEntity findBoard = boardRepository.findByIdWithMemberAndCommentsAndFiles(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board Id", String.valueOf(boardId))
        );
        // 조회수 증가
        findBoard.upViewCount();
        return RecBoardDetailsDTO.fromEntity(findBoard);
    }

    // 게시글 수정
    public RecBoardDetailsDTO update(Long boardId, RecBoardUpdateDTO boardDTO) {
        RecBoardEntity updateBoard = boardRepository.findByIdWithMemberAndCommentsAndFiles(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board Id", String.valueOf(boardId))
        );
        updateBoard.update(boardDTO.getTitle(), boardDTO.getContent());
        return RecBoardDetailsDTO.fromEntity(updateBoard);
    }

    // 게시글 삭제
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }

}
