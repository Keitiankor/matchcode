package com.multicampus.matchcode.controller.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.service.hgdd.PostService;
import com.multicampus.matchcode.service.hgdd.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class PostController {


    @Autowired
    private PostService postService;
    @Autowired
    private ReplyService replyService;

    //게시글 작성창으로 이동
    @GetMapping("/post/insert")
    public String insert(Model model,PostDTO postDTO) {
        model.addAttribute("postDTO", postDTO);

        return "hgdd/insert";
    }

    //게시글 입력한 정보를 db로 이동 동시에 message로 성공 출력과 입력된 url로 이동
    @PostMapping("/post/insert2")
    public String insert2(PostDTO postDTO, Model model) {

        postService.insert(postDTO);;//db저장

        System.out.println("제목: " + postDTO.getTitle());
        System.out.println("내용: " + postDTO.getContent());
        model.addAttribute("message", "글 작성이 완료되었습니다.");//출력되는 메시지
        model.addAttribute("searchUrl", "/post/list");//이동하는 경로

        return "hgdd/message";
    }

    //게시글 목록으로 이동
    @GetMapping("/post/list")
    public String list(Model model, @PageableDefault(page = 0,size = 15,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,String searchKeyword) {

        Page<PostDTO> list = null;

        if(searchKeyword == null) {
            list = postService.list(pageable);  //페이징
        }else {
            list = postService.postlist(searchKeyword, pageable); //검색
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "hgdd/list";
    }

    //게시글 열람
    @GetMapping("/post/view")
    public String view(Model model, Long id) {

        model.addAttribute("post",postService.view(id));
        List<ReplyDTO> replyDTOList= replyService.list(id);
        model.addAttribute("replylist",replyDTOList);
        return "hgdd/view";
    }

    //게시글 수정 페이지 이동
    @GetMapping("/post/correction/{id}")
    public String correction(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.view(id));
        return "hgdd/correction";
    }

    //게시글 수정
    @PostMapping("/post/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, PostDTO postDTO, Model model) {

        PostDTO postTemp = postService.view(id);
        postTemp.setTitle(postDTO.getTitle());
        postTemp.setContent(postDTO.getContent());

        model.addAttribute("message", "글 수정 완료.");
        model.addAttribute("searchUrl", "/post/list");

        postService.insert(postTemp);

        return "hgdd/message";
    }

    //게시글 삭제
    @GetMapping("/post/delete")
    public String delete(Long id, Model model) {
        postService.delete(id);
        model.addAttribute("message", "글 삭제가 완료.");//출력되는 메시지
        model.addAttribute("searchUrl", "/post/list");//이동하는 경로
        return "hgdd/message";
    }

}