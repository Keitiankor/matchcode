package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<PostDTO, Long> {

    //페이징 처리
    Page<PostDTO> findByTitleContaining(String searchKeyword, Pageable pageable);

    //조회수 증가
    @Modifying
    @Query("update post p set p.views = p.views + 1 where p.id = :id")
    int updateView(Long id);

    //좋아요 증가
    @Modifying
    @Query("update post p set p.likes = p.likes + 1 where p.id = :id")
    int likesup(Long id);

    //좋아요 감소
    @Modifying
    @Query("update post p set p.likes = p.likes - 1 where p.id = :id")
    int likesdown(Long id);

    // 좋아요 수가 많은 순서로 게시글 목록 가져오기
    Page<PostDTO> findAllByOrderByLikesDesc(Pageable pageable);
    Page<PostDTO> findByTitleContainingOrderByLikesDesc(String searchKeyword, Pageable pageable);

    //조회수가 많은 순서로 게시글 목록 가져오기
    Page<PostDTO> findAllByOrderByViewsDesc(Pageable pageable);
    Page<PostDTO> findByTitleContainingOrderByViewsDesc(String searchKeyword, Pageable pageable);

    @Modifying
    @Query("update post p set p.declation = p.declation + 1 where p.id = :id")
    int updatedeclation(Long id);

    List<PostDTO> findTop3ByOrderByLikesDesc();

    List<PostDTO> findAllByMemberId(long memberId);
}
