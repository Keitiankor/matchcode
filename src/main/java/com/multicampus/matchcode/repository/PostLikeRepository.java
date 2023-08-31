package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.PostLikeDTO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLikeDTO, Long> {
    PostLikeDTO findByPostIdAndMemberId(Long postId, Long memberId);
}
