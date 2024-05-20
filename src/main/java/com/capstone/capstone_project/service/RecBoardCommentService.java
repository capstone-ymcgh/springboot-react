package com.capstone.capstone_project.service;


import com.capstone.capstone_project.common.exception.ResourceNotFoundException;
import com.capstone.capstone_project.dto.request.recboardcomment.RecBoardCommentDTO;
import com.capstone.capstone_project.dto.response.recboardcomment.RecResBoardCommentDTO;
import com.capstone.capstone_project.entity.MemberEntity;
import com.capstone.capstone_project.entity.RecBoardCommentEntity;
import com.capstone.capstone_project.entity.RecBoardEntity;
import com.capstone.capstone_project.repository.MemberRepository;
import com.capstone.capstone_project.repository.RecBoardCommentRepository;
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
public class RecBoardCommentService {

    private final RecBoardCommentRepository commentRepository;
    private final RecBoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public Page<RecResBoardCommentDTO> getAllComments(Pageable pageable, Long boardId) {
        Page<RecBoardCommentEntity> comments = commentRepository.findAllWithMemberAndBoard(pageable, boardId);
        List<RecResBoardCommentDTO> commentList = comments.getContent().stream()
                .map(RecResBoardCommentDTO::fromEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(commentList, pageable, comments.getTotalElements());
    }

    public RecResBoardCommentDTO write(Long boardId, MemberEntity member, RecBoardCommentDTO writeDto) {
        // board 정보 검색
        RecBoardEntity board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResourceNotFoundException("Board", "Board id", String.valueOf(boardId))
        );
        // member(댓글 작성자) 정보 검색
        MemberEntity commentWriter = memberRepository.findById(member.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member id", String.valueOf(member.getId()))
        );
        // Entity 변환, 연관관계 매핑
        RecBoardCommentEntity comment = RecBoardCommentDTO.ofEntity(writeDto);
        comment.setBoard(board);
        comment.setMember(commentWriter);

        RecBoardCommentEntity saveComment = commentRepository.save(comment);
        return RecResBoardCommentDTO.fromEntity(saveComment);
    }

    public RecResBoardCommentDTO update(Long commentId, RecBoardCommentDTO commentDto) {
        RecBoardCommentEntity comment = commentRepository.findByIdWithMemberAndBoard(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "Comment Id", String.valueOf(commentId))
        );
        comment.update(commentDto.getContent());
        return RecResBoardCommentDTO.fromEntity(comment);
    }

    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
