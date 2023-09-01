package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.DeclationDTO;
import com.multicampus.matchcode.repository.DeclationRepository;
import com.multicampus.matchcode.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeclationService {

    private final DeclationRepository declationRepository;
    private final PostRepository postRepository;

    @Transactional
    public int toggleDeclaration(long postId, long memberId) {
        DeclationDTO declaration = declationRepository.findByPostIdAndMemberId(postId, memberId);

        if (declaration == null) {
            DeclationDTO declarations = DeclationDTO.builder().postId(postId).memberId(memberId).build();
            declationRepository.save(declarations); //새로운 신고 추가
            return postRepository.declarationup(postId); //post 신고 증가
        } else {
            declationRepository.delete(declaration); // 기존 신고 제거
            return postRepository.declationdown(postId); //post 신고감소
        }
    }
}
