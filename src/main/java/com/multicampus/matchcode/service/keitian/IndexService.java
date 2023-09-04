package com.multicampus.matchcode.service.keitian;

import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.repository.MatchRepository;
import com.multicampus.matchcode.repository.PostRepository;
import com.multicampus.matchcode.service.hgdd.PostService;
import com.multicampus.matchcode.service.hyuk.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {

    private final PostRepository postRepository;
    private final MatchRepository matchRepository;

    IndexService(PostRepository postRepository, MatchRepository matchRepository) {
        this.postRepository = postRepository;
        this.matchRepository = matchRepository;
    }

    public List<PostDTO> getIndexPost() {
        return postRepository.findTop5ByOrderByIdDesc();
    }

    public List<MatchDTO> getIndexMatch() {
        return matchRepository.findTop5ByOrderByIdDesc();
    }
}
