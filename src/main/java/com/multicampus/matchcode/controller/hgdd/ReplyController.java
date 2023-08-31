package com.multicampus.matchcode.controller.hgdd;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.ReplyDTO;
import com.multicampus.matchcode.model.request.hgdd.ReplyRequest;
import com.multicampus.matchcode.service.hgdd.ReplyService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private final ReplyService service;

    //댓글 등록
    @PostMapping("/insert/{id}")
    public String replyInsert(
        @PathVariable Long id,
        ReplyRequest request,
        Model model,
        @SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO memberDTO
    ) {
        if (memberDTO != null) {
            service.save(request, memberDTO.getId(), id);
            System.out.println("제목: " + request.getComment());
            System.out.println("게시글 번호: " + id);

            return "redirect:/post/view?id=" + id;
        } else {
            model.addAttribute("message", "로그인을 해야 글 작성이 가능합니다."); //출력되는 메시지
            model.addAttribute("searchUrl", "/login"); //이동하는 경로

            return "hgdd/message";
        }
    }

    //댓글 목록
    @GetMapping("/list/{postId}")
    public List<ReplyDTO> getCommentsForPost(@PathVariable Long postId) {
        return service.list(postId);
    }

    @GetMapping("/delete/{id}/{postId}")
    public String deleteReply(
        @PathVariable Long id,
        Model model,
        @PathVariable Long postId,
        @SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO memberDTO
    ) {
        System.out.println("id: " + id);
        System.out.println("postid: " + postId);
        ReplyDTO reply = service.view(id);

        if (memberDTO != null) { //로그인 확인, 로그인된 id와 게시글 작성자 id 동일한지 확인
            if (reply.getMemberId() == memberDTO.getId()) {
                service.deleteReply(id);
                return "redirect:/post/view?id=" + postId;
            }
        }
        model.addAttribute("message", "작성자가 아닙니다.");
        model.addAttribute("searchUrl", "/post/view?id=" + postId);
        return "hgdd/message";
    }
}
