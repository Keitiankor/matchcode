package com.multicampus.matchcode.controller.hyem;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.model.request.hyem.ApplicationRequest;
import com.multicampus.matchcode.model.request.hyem.TeamCreateRequest;
import com.multicampus.matchcode.model.request.khj.MemberInfoRequest;
import com.multicampus.matchcode.repository.ApplicationRepository;
import com.multicampus.matchcode.repository.RecruitRepository;
import com.multicampus.matchcode.service.hyem.ApplicationService;
import com.multicampus.matchcode.service.hyem.RecruitService;
import com.multicampus.matchcode.service.hyem.TeamMemberService;
import com.multicampus.matchcode.service.hyem.TeamService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import com.multicampus.matchcode.util.enums.hyem.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private RecruitService recruitService;

    // 팀 페이지(리더/멤버/None)
    @GetMapping("/page")
    public String teamPage(Model model,
                           @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) {
        if(memberDTO != null){
            try{
                long memberId = memberDTO.getId();
                TeamMemberDTO teamMemberDTO = teamMemberService.teamMemberInfo(memberId);
                long teamId = teamMemberDTO.getTeamId();
                model.addAttribute("team", teamService.teamView(teamId));
                String mapping = null;
                if (teamMemberService.isTeamLeader(teamId, memberId) == 1) {
                    RecruitDTO recruitDTO = recruitService.recruitViewByTeamId(teamId);
                    model.addAttribute("recruit", recruitDTO);
                    mapping = "hyem/team/teaminformation";
                } else if (teamMemberService.isTeamLeader(teamId, memberId) == 2) {
                    mapping = "hyem/team/teamview";
                }
                return mapping;
            } catch (NullPointerException e) {
                long applicationId = applicationService.findApplicatedId(memberDTO.getId());
                model.addAttribute("applicationId", applicationId);
                return "hyem/team/nullteam";
            }
        } else {
            model.addAttribute("message", "로그인 후 접근이 가능합니다.");
            model.addAttribute("searchUrl", "/login");
            return "hyem/message";
        }
    }

    // 팀 생성하기
    @GetMapping("/create")
    public String createTeam(
            @ModelAttribute("team") TeamDTO teamDTO,
            @ModelAttribute("member") TeamMemberDTO teamMemberDTO,
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO,
            Model model
    ) {
        if ((memberDTO != null)) {
            if (applicationService.memberApplicated(memberDTO.getId())) {
                model.addAttribute("message", "이미 가입 신청한 팀이 있습니다.");
                model.addAttribute("searchUrl", "/recruit/list");
                return "hyem/message";
            } else if (teamMemberService.getPrivilege(memberDTO.getId()) == 1) {
                model.addAttribute("message", "이미 생성한 팀이 있습니다.");
                model.addAttribute("searchUrl", "/recruit/list");
                return "hyem/message";
            }
            model.addAttribute("memberId", memberDTO.getId());
            return "hyem/team/createteam";
        } else {
            model.addAttribute("message", "로그인 후 팀 생성이 가능합니다.");
            model.addAttribute("searchUrl", "/login");
            return "hyem/message";
        }
    }

    @PostMapping("/create")
    public String createTeamPro(
            @ModelAttribute("team") TeamCreateRequest request_team,
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO,
            Model model
    ) throws Exception {
        long teamId = teamService.createTeam(request_team);
        String teamUri = request_team.getUri();
        teamMemberService.addTeamLeader(teamId, memberDTO.getId());
        model.addAttribute("message", "팀 생성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/team/view/" + teamUri + "/"+ teamId);
        return "hyem/message";
    }

    // 팀 리스트
    @GetMapping("/list")
    public String teamList(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        Page<TeamDTO> list = teamService.teamList(pageable);
        int nowPage = list
                .getPageable()
                .getPageNumber() + 1;

        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());
        model.addAttribute("list", list);

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "hyem/team/teamlist";
    }

    // 팀 상세 정보 열람
    @GetMapping("/view/{uri}/{id}")
    public String teamView(@PathVariable("uri") String uri, @PathVariable("id") Long id, Model model,
                           @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = false) MemberDTO memberDTO) {
        model.addAttribute("team", teamService.teamView(id));
        if(teamMemberService.isTeamLeader(id, memberDTO.getId()) == 1) {
            return  "hyem/team/teaminformation";
        } else if (teamMemberService.isTeamLeader(id, memberDTO.getId()) == 2) {
            return  "hyem/team/teamview";
        } else if (teamMemberService.isTeamMember(memberDTO.getId())) {
            return "hyem/team/";
        } else {
            model.addAttribute("message", "접근 권한이 없습니다.");
            model.addAttribute("searchUrl", "/recruit/list"); // 임시 경로이므로 추후에 수정
            return "hyem/message";
        }
    }

    // 팀 정보 수정 페이지
    @GetMapping("/modify/{uri}/{id}")
    public String teamModify(@PathVariable("uri") String uri, @PathVariable("id") Long id, Model model) {
        model.addAttribute("team", teamService.teamView(id));
        return "hyem/team/teammodify";
    }

    // 팀 정보 수정
    @PostMapping("/modify/complete/{id}")
    public String teamUpdate(@PathVariable("id") Long id, String uri, TeamCreateRequest request, Model model) throws Exception {
        teamService.teamUpdate(id, request);
        model.addAttribute("message", "팀 정보 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/team/view/"+ uri + "/" + Long.toString(id));
        return "hyem/message";
    }

    // 팀 정보 삭제
    @DeleteMapping("/delete/{uri}/{id}")
    public String teamDelete(@PathVariable("id") long id, @PathVariable("uri") String uri, Model model) throws Exception {
        model.addAttribute("message", "정말로 팀을 삭제하시겠습니까?");
        model.addAttribute("confirmUrl", "/team/deleteconfirmed/" + id);
        model.addAttribute("cancelUrl", "/team/view/" + uri + "/" + Long.toString(id));
        return "hyem/confirmmessage";
    }

    // 팀 정보 삭제 처리
    @PostMapping("/deleteconfirmed/{id}")
    public String deleteConfirmed(@PathVariable("id") Long id, Model model) {
        teamService.teamDelete(id);
        model.addAttribute("message", "팀 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/");
        return "hyem/message";
    }

    //enum 모델 추가
    //팀 생성할 때만 필요한 부분이라 수정 필요
    @ModelAttribute("sports")
    private Sport[] sports() {
        return Sport.values();
    }

    @ModelAttribute("ages")
    private Age[] ages() {
        return Age.values();
    }

    @ModelAttribute("genders")
    private Gender[] genders() {
        return Gender.values();
    }

    @ModelAttribute("weeks")
    private Week[] weeks() {
        return Week.values();
    }

    @ModelAttribute("times")
    private Time[] times() {
        return Time.values();
    }
}
