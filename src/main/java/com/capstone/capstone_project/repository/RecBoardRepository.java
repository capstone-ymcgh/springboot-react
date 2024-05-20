package com.capstone.capstone_project.repository;


import com.capstone.capstone_project.entity.RecBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RecBoardRepository extends JpaRepository<RecBoardEntity, Long> {

    // 게시글 상세 조회, @BatchSize : Comments와 Files는 필요할 때 in 절로 가져옴
    @Query(value = "SELECT b FROM RecBoardEntity b JOIN FETCH b.member WHERE b.id = :boardID")
    Optional<RecBoardEntity> findByIdWithMemberAndCommentsAndFiles(Long boardID);

    // 첫 페이징 화면("/")
    @Query(value = "SELECT b FROM RecBoardEntity b JOIN FETCH b.member")
    Page<RecBoardEntity> findAllWithMemberAndComments(Pageable pageable);

    // 제목 검색
    @Query(value = "SELECT b FROM RecBoardEntity b JOIN FETCH b.member WHERE b.title LIKE %:title%")
    Page<RecBoardEntity> findAllTitleContaining(String title, Pageable pageable);

    // 내용 검색
    @Query(value = "SELECT b FROM RecBoardEntity b JOIN FETCH b.member WHERE b.content LIKE %:content%")
    Page<RecBoardEntity> findAllContentContaining(String content, Pageable pageable);

    // 작성자 검색
    @Query(value = "SELECT b FROM RecBoardEntity b JOIN FETCH b.member WHERE b.member.username LIKE %:username%")
    Page<RecBoardEntity> findAllUsernameContaining(String username, Pageable pageable);

}
