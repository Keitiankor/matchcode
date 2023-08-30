package com.multicampus.matchcode.repository;


import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.PostLikeDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PostLikeRepository extends JpaRepository<PostLikeDTO, Long> {



    PostLikeDTO findByPostIdAndUserId(Long postId, Long memberId);
}
