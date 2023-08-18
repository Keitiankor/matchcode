package com.multicampus.matchcode.controller.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.service.hyem.TeamService;
import com.multicampus.matchcode.util.enums.hyem.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
