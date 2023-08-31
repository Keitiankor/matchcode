package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.model.request.hgdd.ReplyRequest;
import com.multicampus.matchcode.repository.PostRepository;
import com.multicampus.matchcode.repository.ReplyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    PostRepository postRepository;

    //댓글 저장
    public Long save(ReplyRequest requst, long memberId) {
        ReplyDTO replyDTO = ReplyDTO
                .builder()
                .post(requst.getPost())
                .userId(memberId)
                .comment(requst.getComment())
                .build();
        replyRepository.save(replyDTO);
        return null;
    }

    public ReplyDTO view(long id) {
        return replyRepository.findById(id).get();
    }

    //댓글 리스트
    public List<ReplyDTO> list(Long postId) {
        return replyRepository.findByPostId(postId);
    }

    //댓글 삭제
    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}
