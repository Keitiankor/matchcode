package com.multicampus.matchcode.controller.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.model.request.hgdd.ReplyRequest;
import com.multicampus.matchcode.service.hgdd.PostService;
import com.multicampus.matchcode.service.hgdd.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private final ReplyService service;


        //댓글 등록
        @PostMapping("/insert/{id}")
        public String replyInsert(@PathVariable Long id, ReplyRequest request) {
            service.save(request);
            System.out.println("제목: " + request.getComment());
            System.out.println("게시글 번호: " + request.getPost());
            System.out.println("게시글 번호: " + id);

            return "redirect:/post/view?id=" + id;
        }
        //댓글 목록
        @GetMapping("/list/{postId}")
        public List<ReplyDTO> getCommentsForPost(@PathVariable Long postId) {
            return service.list(postId);
        }

    @GetMapping("/delete/{id}/{postId}")
    public String deleteReply(@PathVariable Long id,@PathVariable Long postId) {
        System.out.println("id: " + id);
        System.out.println("postid: " + postId);
        service.deleteReply(id);
        /*selectedReplyId = null; // 댓글 삭제 후 선택 상태 초기화*/
        return "redirect:/post/view?id=" + postId;
    }

}