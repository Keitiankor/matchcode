package com.multicampus.matchcode.service.hgdd;

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
    public Long save(ReplyRequest requst) {
        ReplyDTO replyDTO = ReplyDTO
            .builder()
            .post(requst.getPost())
                //.userId(requst.getUserId())
            .comment(requst.getComment())
            .build();
        replyRepository.save(replyDTO);
        return null;
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
