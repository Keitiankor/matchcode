package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public void insert(ReplyDTO replyDTO) {

        replyRepository.save(replyDTO);
    }

}
