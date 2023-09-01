package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.PostLikeDTO;
import com.multicampus.matchcode.repository.PostLikeRepository;
import com.multicampus.matchcode.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostLikeService {

    private final PostLikeRepository likeRepository;
    private final PostRepository postRepository;

    @Transactional
    public int toggleLike(long postId, long memberId) {
        PostLikeDTO like = likeRepository.findByPostIdAndMemberId(postId, memberId);

        if (like == null) {
            PostLikeDTO like2 = PostLikeDTO.builder()
                                           .postId(postId)
                                           .memberId(memberId)
                                           .build();
            likeRepository.save(like2); // 새로운 좋아요 추가
            return postRepository.likesup(postId); //post 좋아요 증가
        } else {
            likeRepository.delete(like); // 기존 좋아요 제거
            return postRepository.likesdown(postId); //post 좋아요 감소
        }
    }
}
