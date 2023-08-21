package com.multicampus.matchcode.controller.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.service.hyem.TeamService;
import com.multicampus.matchcode.util.enums.hyem.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;
    private Sport sport;
    private Age age;
    private Gender gender;
    private Week week;
    private Time time;


    // 팀 생성 페이지
    @GetMapping("/create")
    public String addTeam(Model model) {
        TeamDTO teamDTO = teamService.create("","","","","","","");
        model.addAttribute("team", teamDTO);
        return "hyem/createteam";
    }

    // 팀 생성하기
    @PostMapping("/addteam")
    public String createTeam(@ModelAttribute("team") TeamDTO teamDTO, Model model) throws Exception{
        teamService.save(teamDTO);
        model.addAttribute("message", "팀 생성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/team/create");
        System.out.println("id : " + teamDTO.getId());
        System.out.println("teamName : " + teamDTO.getTeamName());
        System.out.println("uri : " + teamDTO.getUri());
        System.out.println("selected sport : " + teamDTO.getSportsId());
        System.out.println("gender average : " + teamDTO.getAverageGender());
        System.out.println("age average : " + teamDTO.getAverageAge());
        System.out.println("week average : " + teamDTO.getUseWeek());
        System.out.println("time average : " + teamDTO.getUseTime());

        return "hyem/message";
    }

    // 팀 리스트
    @GetMapping("/list")
    public String teamList(Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                           String searchKeyword) {

        Page<TeamDTO> list = teamService.teamList(pageable);;

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "hyem/teamlist";
    }

    // 팀 상세 정보 열람
    @GetMapping("/oneteam/{id}")
    public String teamView(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.teamView(id));
        return "hyem/oneteam";
    }

    //enum 모델 추가
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
