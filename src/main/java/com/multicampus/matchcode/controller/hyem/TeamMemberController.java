package com.multicampus.matchcode.controller.hyem;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.service.hyem.ApplicationService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class TeamMemberController {

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private TeamService teamService;

    // 팀원 추가하기
    @PostMapping("/addteammember")
    public String addTeamMember(Long id,
            //ApplicationRequest request,
            Model model
    ) throws Exception {
        ApplicationDTO applicationDTO = applicationService.applicationView(id);

        long teamId = applicationDTO.getTeamId();
        long memberId = applicationDTO.getMemberId();

        teamMemberService.addTeamMember(teamId, memberId);
        applicationService.applicationCancel(id);

        model.addAttribute("message", "가입을 수락하였습니다.");
        model.addAttribute("searchUrl", "/team/page");
        return "hyem/message";
    }

    // 팀별 팀멤버 리스트
    @GetMapping("/team/page/{uri}/memberlist")
    public String teamList(
            @PathVariable("uri") String uri,
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        long teamId = teamMemberService.getTeamId(memberDTO.getId());
        model.addAttribute("team",teamService.teamView(teamId));
        model.addAttribute("teamId", teamId);
        Page<Object[]> list = teamMemberService.teamMemberList3(pageable, teamId);
        int nowPage = list
                .getPageable()
                .getPageNumber() + 1;

        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        model.addAttribute("list", list);

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "hyem/teammember/teammemberlist";
    }

    // 팀원 상세 정보 열람
    @GetMapping("/team/page/{uri}/{name}")
    public String teamView(@PathVariable("uri") String uri, @PathVariable("name") String name,
                           long teamId, Model model,
                           @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) {
        if(teamMemberService.isTeamMember(memberDTO.getId())) {
            long teamMemberId = teamMemberService.getTeamMemberName(teamId, name);
            Object teamMemberInfo = teamMemberService.getMemberInfo(teamId, teamMemberId);
            System.out.println("info : " + teamMemberInfo);
            model.addAttribute("teamMemberId", teamMemberId);
            model.addAttribute("teamMemberInfo", teamMemberInfo);
            model.addAttribute("teamId", teamMemberService.getTeamId(memberDTO.getId()));
            //model.addAttribute("memberId", teamService.getTeamMemberName(teamId, ));
            return  "hyem/teammember/memberinfo";
        }
        else {
            model.addAttribute("message", "접근 권한이 없습니다.");
            model.addAttribute("searchUrl", "/team/list"); // 임시 경로이므로 추후에 수정
            return "hyem/message";
        }
    }

    /*

    // 팀 정보 삭제
    @DeleteMapping("/delete/{id}")
    public String teamDelete(@PathVariable("id") long id, Model model) throws Exception {
        model.addAttribute("message", "정말로 팀을 삭제하시겠습니까?");
        model.addAttribute("confirmUrl", "/team/deleteconfirmed/" + id);
        model.addAttribute("cancelUrl", "/team/list");
        return "hyem/confirmmessage";
    }

    // 팀 정보 삭제 처리
    @PostMapping("/deleteconfirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") Long id, Model model) {
        teamService.teamDelete(id);

        model.addAttribute("message", "팀 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/team/list");
        return "hyem/message";
    }
    }*/
}
