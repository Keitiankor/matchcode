package com.multicampus.matchcode.controller.hyem;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.RecruitDTO;
import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.request.hyem.RecruitListRequest;
import com.multicampus.matchcode.model.request.hyem.RecruitPostRequest;
import com.multicampus.matchcode.service.hyem.RecruitService;
import com.multicampus.matchcode.service.hyem.TeamMemberService;
import com.multicampus.matchcode.service.hyem.TeamService;
import com.multicampus.matchcode.util.constants.SessionConstant;
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

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    // 모집글 작성 매핑
    @GetMapping("/write/{teamid}")
    public String writeRecruit(@PathVariable("teamid") Long teamId, Model model) {
        if (recruitService.isRecruitExist(teamId)) {
            model.addAttribute("message", "이미 모집중입니다.");
            model.addAttribute("searchUrl", "/team/list"); // 임시 mapping 주소
            return "hyem/message";
        } else {
            RecruitDTO recruitDTO = new RecruitDTO();
            model.addAttribute("recruit", recruitDTO);
            return "hyem/recruit/writerecruit";
        }
    }

    // 모집글 작성 처리
    @PostMapping("/write/{id}")
    public String addRecruit(
            @ModelAttribute("recruit") RecruitPostRequest request, Long teamId, Model model) {
        model.addAttribute("teamid", teamId);
        recruitService.save(request);
        System.out.println("content : " + request.getContent());
        return "redirect:/recruit/list";
    }

    // 모집글 리스트
    @GetMapping("/list")
    public String recruitList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {
        Page<RecruitListRequest> list = recruitService.getRecruitsWithTeamNames();

        model.addAttribute("list", list);

        int nowPage = list.getPageable()
                          .getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "hyem/recruit/recruitlist";
    }

    // 모집글 상세 페이지
    @GetMapping("/view/{id}")
    public String recruitView(
            @PathVariable("id") Long id,
            Model model,
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) {

        if (memberDTO != null) {
            RecruitDTO recruitDTO = recruitService.recruitView(id);
            TeamDTO teamDTO = teamService.teamView(recruitDTO.getTeamId());
            model.addAttribute("recruit", recruitDTO);
            model.addAttribute("team", teamDTO);
            return "hyem/recruit/recruitview";
        } else {
            model.addAttribute("message", "로그인 후 열람이 가능합니다.");
            model.addAttribute("searchUrl", "/login");
            return "hyem/message";
        }
    }

    // 모집글 수정 페이지
    @GetMapping("/modify/{id}")
    public String recruitModify(@PathVariable("id") Long id, Model model,
                                @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) {
        RecruitDTO recruitDTO = recruitService.recruitView(id);
        model.addAttribute("recruit", recruitDTO);
        if(teamMemberService.isTeamLeader(recruitDTO.getTeamId(), memberDTO.getId()) != 0) {
            return "hyem/recruit/modifyrecruit";
        } else {
            model.addAttribute("message", "수정 권한이 없습니다.");
            model.addAttribute("searchUrl", "/recruit/list");
            return "hyem/message";
        }
    }

    // 모집글 내용 수정
    @PostMapping("/modify/complete/{id}")
    public String recruitUpdate(
            @RequestParam("id") Long id,
            @ModelAttribute("recruit") RecruitPostRequest request,
            Model model) throws Exception {
        recruitService.recruitUpdate(id, request);
        model.addAttribute("message", "모집글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/recruit/list");
        return "hyem/message";
    }

    // 모집글 삭제
    @DeleteMapping("/delete/{id}")
    public String recruitDelete(@PathVariable("id") long id, Model model,
                                @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) throws Exception {
        RecruitDTO recruitDTO = recruitService.recruitView(id);
        if(teamMemberService.isTeamLeader(recruitDTO.getTeamId(), memberDTO.getId()) != 0) {
            model.addAttribute("message", "정말로 모집글을 삭제하시겠습니까?");
            model.addAttribute("confirmUrl", "/recruit/deleteconfirmed/" + id);
            model.addAttribute("cancelUrl", "/recruit/list");
            return "hyem/confirmmessage";
        } else {
            model.addAttribute("message", "권한이 없습니다.");
            model.addAttribute("searchUrl", "/recruit/list");
            return "hyem/message";
        }

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
