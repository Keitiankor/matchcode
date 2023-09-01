package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PostLikeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLikeDTO, Long> {
    PostLikeDTO findByPostIdAndMemberId(long postId, long memberId);
}
