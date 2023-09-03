package com.multicampus.matchcode.controller.hyuk;

import com.multicampus.matchcode.model.entity.RecruitDTO;
import com.multicampus.matchcode.repository.MatchMemberRepository;
import com.multicampus.matchcode.service.hyem.RecruitService;
import com.multicampus.matchcode.service.hyem.TeamMemberService;
import com.multicampus.matchcode.service.hyem.TeamService;
import com.multicampus.matchcode.service.hyuk.MatchMemberService;
import com.multicampus.matchcode.service.hyuk.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/match")
public class MatchMemberController {
    @Autowired
    private MatchMemberRepository matchMemberRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchMemberService matchMemberService;

    // 모집글 작성 매핑
    @GetMapping("/write/{matchid}")
    public String writeMatch(@PathVariable("matchid") Long matchId, Model model) {
        if (matchMemberService.isMatchExist(matchId)) {
            model.addAttribute("message", "이미 참여중입니다.");
            model.addAttribute("searchUrl", "/match/list"); // 임시 mapping 주소
            return "hyem/message";
        } else {
            RecruitDTO recruitDTO = new RecruitDTO();
            model.addAttribute("recruit", recruitDTO);
            return "hyem/recruit/writerecruit";
        }
    }
}
