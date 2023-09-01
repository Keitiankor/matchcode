package com.multicampus.matchcode.controller.hgdd;


import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.service.hgdd.PostLikeService;
import com.multicampus.matchcode.service.hgdd.PostService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping("/like")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService likeService;
    private final PostService postService;

    @PostMapping("/{postId}")
    public String toggleLike(
            @PathVariable("postId") Long postId,
            Model model,
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO
    ) {
        PostDTO post = postService.view(postId);

        if (memberDTO != null) {
            System.out.println(memberDTO.getId());
            System.out.println(postId);
            likeService.toggleLike(postId, memberDTO.getId()); // 게시글 좋아요 토글 서비스 호출
            return "redirect:/post/view?id=" + postId; // 게시글 상세 페이지로 이동
        } else {
            model.addAttribute("message", "로그인을 해야 글 작성이 가능합니다."); //출력되는 메시지
            model.addAttribute("searchUrl", "/login"); //이동하는 경로

            return "hgdd/message";
        }
    }
}