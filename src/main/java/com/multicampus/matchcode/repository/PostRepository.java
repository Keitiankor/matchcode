package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<PostDTO, Long> {
    Page<PostDTO> findByTitleContaining(String searchKeyword, Pageable pageable);

    @Modifying
    @Query("update post p set p.views = p.views + 1 where p.id = :id")
    int updateView(Long id);

}
