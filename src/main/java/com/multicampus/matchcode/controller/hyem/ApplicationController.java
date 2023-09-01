package com.multicampus.matchcode.controller.hyem;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.request.hyem.ApplicationRequest;
import com.multicampus.matchcode.service.hyem.ApplicationService;
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
@RequestMapping("/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // 가입 신청하기
    @GetMapping("/join/{teamid}")
    public String joinTeam(@PathVariable("teamid") Long teamId, @ModelAttribute("join") ApplicationDTO applicationDTO, @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO, Model model) {
        model.addAttribute("memberid", memberDTO.getId());
        return "hyem/application/joinapplication";
    }

    @PostMapping("/join/{teamid}/{id}")
    public String recruitPostWrite(@PathVariable("teamid") Long teamId, @ModelAttribute("join") ApplicationRequest request, @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO, Model model) throws Exception {
        //model.addAttribute("memberId", memberDTO.getId());
        applicationService.save(request, memberDTO.getId());
        model.addAttribute("message", "가입 신청이 완료되었습니다.");
        model.addAttribute("searchUrl", "/application/list");
        System.out.println("가입신청한 user name : " + memberDTO.getName());
        return "hyem/message";
    }

    // 가입 신청 리스트
    @GetMapping("/list")
    public String applicationList(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<ApplicationDTO> list = applicationService.applicationList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "hyem/application/applicationlist";
    }

    // 가입 신청 정보
    @GetMapping("/view/{id}")
    public String applicationView(@PathVariable Long id, Model model) {
        model.addAttribute("join", applicationService.applicationView(id));
        return "hyem/application/applicationview";
    }

    // 가입 신청 내용 수정
    @GetMapping("/modify/{id}")
    public String applicationModify(@PathVariable("id") Long id, Model model) {
        model.addAttribute("join", applicationService.applicationView(id));
        return "hyem/application/modifyintroduction";
    }

    @PostMapping("/modify/complete/{id}")
    public String applicationUpdate(@PathVariable("id") Long id, @ModelAttribute("join") ApplicationRequest request, Model model) throws Exception {
        applicationService.applicationUpdate(id, request);
        model.addAttribute("message", "가입 신청 내용 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/application/list");
        return "hyem/message";
    }

    // 가입신청 취소
    @DeleteMapping("/cancel/{id}")
    public String applicationCancel(@PathVariable("id") long id, Model model) throws Exception {
        model.addAttribute("message", "정말로 가입을 취소하시겠습니까?");
        model.addAttribute("confirmUrl", "/application/deleteconfirmed/" + id);
        model.addAttribute("cancelUrl", "/application/list");
        return "hyem/confirmmessage";
    }

    // 팀 정보 삭제 처리
    @PostMapping("/deleteconfirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") Long id, Model model) {
        applicationService.applicationCancel(id);

        model.addAttribute("message", "가입 취소가 완료되었습니다.");
        model.addAttribute("searchUrl", "/application/list");
        return "hyem/message";
    }
}
