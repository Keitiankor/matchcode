package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.repository.PostRepository;
import com.multicampus.matchcode.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPostService {

    @Autowired
    PostRepository post;

    @Autowired
    ReplyRepository reply;

    public List<PostDTO> getMyPostsByMemberId(long memberId) {
        List<PostDTO> MyPosts = post.findAllByMemberId(memberId);
        return MyPosts;
    }

    public List<ReplyDTO> getMyRepliesByMemberId(long memberId) {
        List<ReplyDTO> MyReplies = reply.findAllByMemberId(memberId);
        return MyReplies;
    }
}
