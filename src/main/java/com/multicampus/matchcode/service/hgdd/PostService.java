package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.repository.MemberRepository;
import com.multicampus.matchcode.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;



@RequiredArgsConstructor
@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;

    //게시글 작성
    public void insert(PostDTO postDTO) {
         postRepository.save(postDTO);
    }


    //게시글 리스트
    public Page<PostDTO> list(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    //페이징 및 검색
    public Page<PostDTO> postlist(String SearchKeyword, Pageable pageable){
        return postRepository.findByTitleContaining(SearchKeyword, pageable);
    }


    //게시글 열람
    public PostDTO view(long id) {
        return postRepository.findById(id).get();
    }


    //게시글 삭제
    public void delete(long id) {
        postRepository.deleteById(id);
    }

}
