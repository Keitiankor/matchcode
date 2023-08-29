package com.multicampus.matchcode.service.hgdd;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.request.hgdd.PostInsertRequest;
import com.multicampus.matchcode.model.request.hgdd.PostUpdateRequest;
import com.multicampus.matchcode.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    //게시글 작성
   public void insert(PostInsertRequest request,long userid) {
       PostDTO dto =PostDTO
               .builder()
               .title(request.getTitle())
               .content(request.getContent())
               .userId(userid)
               .createdDate(request.getCreatedDate())
               .privates(request.isPrivates())
               .build();
       postRepository.save(dto);
   }


    // 게시글 리스트
    public Page<PostDTO> list(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    // 페이징 및 검색
    public Page<PostDTO> postlist(String SearchKeyword, Pageable pageable) {
        return postRepository.findByTitleContaining(SearchKeyword, pageable);
    }

    // 게시글 열람
    public PostDTO view(long id) {
        return postRepository.findById(id).get();
    }


    //게시글 업데이트
    public PostDTO update(long id, PostUpdateRequest request,long userId) {
        PostDTO post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다"));

        PostDTO update = PostDTO
                .builder()
                .id(post.getId())
                .userId(userId)
                .content(request.getContent())
                .title(request.getTitle())
                .views(post.getViews())
                .createdDate(post.getCreatedDate())
                .editedDate(request.getEditedDate())
                .privates(request.isPrivates())
                .build();

        return postRepository.save(update);
    }

    //조회수 증가
    @Transactional
    public int views(Long id) {
        return postRepository.updateView(id);
    }

    // 게시글 삭제
    public void delete(long id) {
        postRepository.deleteById(id);
    }

}
