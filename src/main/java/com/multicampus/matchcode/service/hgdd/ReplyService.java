package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.repository.PostRepository;
import com.multicampus.matchcode.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private  final ReplyRepository replyRepository;
    private  final PostRepository postRepository;

    //댓글 저장
    public void insert(ReplyDTO replyDTO) {
        replyRepository.save(replyDTO);
    }



    //댓글 출력
    public List<ReplyDTO> list(Long postId) {
      return replyRepository.findAllByPostId(postId);
    }

}
