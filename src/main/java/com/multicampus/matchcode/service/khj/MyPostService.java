package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.repository.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPostService {

    @Autowired
    PostRepository post;

    public List<PostDTO> getMyPostsByMemberId(long memberId) {
        List<PostDTO> MyPosts = post.findAllByMemberId(memberId);
        return MyPosts;
    }
}
