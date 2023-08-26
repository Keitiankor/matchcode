package com.multicampus.matchcode.controller.hyem;

import com.multicampus.matchcode.model.entity.RecruitDTO;
import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.request.hyem.RecruitPostRequest;
import com.multicampus.matchcode.service.hyem.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    // 모집글 작성 매핑
    @GetMapping("/write/{teamid}")
    public String writeRecruit(@PathVariable("teamid") Long teamId, Model model) {
        RecruitDTO recruitDTO = new RecruitDTO();
        model.addAttribute("teamid", teamId);
        model.addAttribute("recruit", recruitDTO);
        return "hyem/recruit/writerecruit";
    }

    // 모집글 작성 처리
    @PostMapping("/write/{id}")
    public String addRecruit(@ModelAttribute("recruit") RecruitPostRequest request,Long teamId, Model model) {
        model.addAttribute("teamid", teamId);
        recruitService.save(request);
        System.out.println("content : " + request.getContent());
        return "redirect:/recruit/list";
    }

    // 모집글 리스트
    @GetMapping("/list")
    public String recruitList(
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
        Model model
    ) {
        TeamDTO teamDTO = new TeamDTO();
        model.addAttribute("team", teamDTO);

        Page<RecruitDTO> list = recruitService.recruitList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "hyem/recruit/recruitlist";
    }

    // 모집글 상세 페이지
    @GetMapping("/view/{id}")
    public String recruitView(@PathVariable("id") Long id, Model model) {
        RecruitDTO dto = recruitService.recruitView(id);

        model.addAttribute("recruit", dto);
        return "hyem/recruit/recruitview";
    }

    // 모집글 삭제
    @DeleteMapping("/delete/{id}")
    public String recruitDelete(@PathVariable("id") long id, Model model) throws Exception {
        model.addAttribute("message", "정말로 모집글을 삭제하시겠습니까?");
        model.addAttribute("confirmUrl", "/recruit/deleteconfirmed/" + id);
        model.addAttribute("cancelUrl", "/recruit/list");
        return "hyem/confirmmessage";
    }

    // 모집글 삭제 처리
    @PostMapping("/deleteconfirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") Long id, Model model) {
        recruitService.recruitDelete(id);

        model.addAttribute("message", "모집글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/recruit/list");
        return "hyem/message";
    }
}
