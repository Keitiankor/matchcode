package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.repository.MemberRepository;
import com.multicampus.matchcode.repository.PostRepository;

import com.multicampus.matchcode.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PostService {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReplyRepository replyRepository;

    //게시글 작성
    public void insert(PostDTO postDTO) {
         postRepository.save(postDTO);
    }


    //게시글 리스트
    public Page<PostDTO> list(Pageable pageable) {return postRepository.findAll(pageable);}

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

    public Long getPostIdByReplyId(Long replyId) {
        Optional<ReplyDTO> optionalReply = replyRepository.findById(replyId);
        if (optionalReply.isPresent()) {
            ReplyDTO reply = optionalReply.get();
            return reply.getPost().getId();
        }
        return null; // 댓글이 없는 경우 등의 예외 처리를 수행하도록 수정 가능
    }


}
