package com.multicampus.matchcode.controller.hgdd;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PostDTO;
import com.multicampus.matchcode.model.entity.PostLikeDTO;
import com.multicampus.matchcode.model.request.hgdd.PostInsertRequest;
import com.multicampus.matchcode.model.request.hgdd.PostUpdateRequest;
import com.multicampus.matchcode.service.hgdd.PostService;
import com.multicampus.matchcode.service.hgdd.ReplyService;
import com.multicampus.matchcode.util.constants.SessionConstant;
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

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private ReplyService replyService;

    //게시글 작성창으로 이동
    @GetMapping("/insert")
    public String insert(Model model, PostDTO postDTO, @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) {
        model.addAttribute("postDTO", postDTO);

        if (memberDTO != null) {
            System.out.println(memberDTO.getId());
            return "hgdd/insert"; // 로그인한 사용자인 경우 작성 페이지로 이동
        } else {
            model.addAttribute("message", "로그인을 해야 글 작성이 가능합니다."); //출력되는 메시지
            model.addAttribute("searchUrl", "/login"); //이동하는 경로

            return "hgdd/message";
        }
    }

    //게시글 입력한 정보를 db로 이동 동시에 message로 성공 출력과 입력된 url로 이동   //두번째 방법에서 id가 0으로 들어가서 첫번째로 일단 바꿨습니다.
    @PostMapping("/insert2")
    public String insert2(@ModelAttribute("postDTO") PostInsertRequest request, Model model, @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) {
        postService.insert(request, memberDTO); //db저장

        System.out.println("제목: " + request.getTitle());
        System.out.println("내용: " + request.getContent());

        model.addAttribute("message", "글 작성이 완료되었습니다."); //출력되는 메시지
        model.addAttribute("searchUrl", "/post/list"); //이동하는 경로

        return "hgdd/message";
    }

    //게시글 목록
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(name = "sortBy", defaultValue = "id") String sortBy, // 드롭다운 값 받아옴
                       String searchKeyword) {
        Page<PostDTO> list = null;

        if ("likes".equals(sortBy)) {
            if (searchKeyword == null) { //최신순
                list = postService.listByLikes(pageable);
            } else {
                list = postService.postlistByLikes(searchKeyword, pageable);
            }
        } else if ("views".equals(sortBy)) { //좋아요순
            if (searchKeyword == null) {
                list = postService.listByViews(pageable);
            } else {
                list = postService.postlistByViews(searchKeyword, pageable);
            }
        } else {
            if (searchKeyword == null) { //조회수 순
                list = postService.list(pageable);
            } else {
                list = postService.postlist(searchKeyword, pageable);
            }
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        List<PostDTO> top3ByLikes = postService.listTop3ByLikes(); //좋아요 많은 3개
        model.addAttribute("top3ByLikes", top3ByLikes);
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "hgdd/list";
    }

    //게시글 열람
    @GetMapping("/view")
    public String view(Model model, Long id, PostLikeDTO likeDTO, @SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO memberDTO) {
        PostDTO post = postService.view(id);

        model.addAttribute("post", postService.view(id));
        model.addAttribute("likeDTO", likeDTO);
        model.addAttribute("list", replyService.list(id));
        if (memberDTO != null) {
            if (post.isPrivates()) { //비공개 여부 확인
                if (post.getMemberId() == memberDTO.getId()) { //로그인 확인, 로그인된 id와 게시글 작성자 id 동일한지 확인
                    model.addAttribute("post", post);
                    postService.views(id); //조회수 증가
                    return "hgdd/view"; // 게시글 조회 페이지 (view.html)
                } else {
                    model.addAttribute("message", "비 공개 글입니다.");
                    model.addAttribute("searchUrl", "/post/list");

                    return "hgdd/message";
                }
            }
        } else if (post.isPrivates()) {
            model.addAttribute("message", "비 공개 글입니다.");
            model.addAttribute("searchUrl", "/post/list");

            return "hgdd/message";
        }
        postService.views(id); //조회수 증가
        return "hgdd/view"; // 게시글 조회 페이지 (view.html)
    }

    //게시글 수정 페이지 이동
    @GetMapping("/correction/{id}")
    public String correction(@PathVariable("id") Long id, Model model, @SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO memberDTO) {
        PostDTO post = postService.view(id);

        if (memberDTO != null) { //로그인 확인, 로그인된 id와 게시글 작성자 id 동일한지 확인
            if (post.getMemberId() == memberDTO.getId()) {
                model.addAttribute("post", postService.view(id));
                return "hgdd/correction";
            }
        }

        model.addAttribute("message", "작성자가 아닙니다.");
        model.addAttribute("searchUrl", "/post/view?id=" + id);
        return "hgdd/message";
    }

    //게시글 수정
    @PostMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, PostUpdateRequest request, Model model) {
        postService.update(id, request);
        model.addAttribute("message", "글 수정 완료.");
        model.addAttribute("searchUrl", "/post/list");

        return "hgdd/message";
    }

    //게시글 삭제
    @GetMapping("/delete")
    public String delete(Long id, Model model) {
        postService.delete(id);
        model.addAttribute("message", "글 삭제가 완료."); //출력되는 메시지
        model.addAttribute("searchUrl", "/post/list"); //이동하는 경로
        return "hgdd/message";
    }

    //신고
    @PostMapping("/declation/{postId}")
    public String reportPost(@PathVariable Long postId, Model model, @SessionAttribute(name = SessionConstant.MEMBER_ID, required = false) MemberDTO memberDTO) {
        if (memberDTO != null) {
            System.out.println(memberDTO.getId());
            postService.declations(postId);
            return "redirect:/post/view?id=" + postId;
        } else {
            model.addAttribute("message", "로그인을 해야 글 작성이 가능합니다."); //출력되는 메시지
            model.addAttribute("searchUrl", "/login"); //이동하는 경로
            return "hgdd/message";
        }
    }
}
