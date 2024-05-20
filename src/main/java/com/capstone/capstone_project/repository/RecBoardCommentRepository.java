package com.capstone.capstone_project.repository;

import com.capstone.capstone_project.entity.RecBoardCommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecBoardCommentRepository extends JpaRepository<RecBoardCommentEntity, Long> {

    @Query(value = "SELECT c FROM RecBoardCommentEntity c JOIN FETCH c.member JOIN FETCH c.board b WHERE b.id = :boardId")
    Page<RecBoardCommentEntity> findAllWithMemberAndBoard(Pageable pageable, Long boardId);

    @Query(value = "SELECT c FROM RecBoardCommentEntity c JOIN FETCH c.member m JOIN FETCH c.board b WHERE c.id = :commentId")
    Optional<RecBoardCommentEntity> findByIdWithMemberAndBoard(Long commentId);

}
