package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ReplyDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<ReplyDTO, Long> {
    List<ReplyDTO> findByPostId(Long postId);
}
