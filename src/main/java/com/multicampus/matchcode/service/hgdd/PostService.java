package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;

    //게시글 작성
    public void insert(PostDTO postDTO) {

        postRepository.save(postDTO);
    }

    //게시글 리스트
    public List<PostDTO> list() {
        return postRepository.findAll();
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
