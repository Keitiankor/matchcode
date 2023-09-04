package com.multicampus.matchcode.controller.hyuk;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.repository.MatchMemberRepository;
import com.multicampus.matchcode.service.hyem.RecruitService;
import com.multicampus.matchcode.service.hyem.TeamMemberService;
import com.multicampus.matchcode.service.hyem.TeamService;
import com.multicampus.matchcode.service.hyuk.MatchMemberService;
import com.multicampus.matchcode.service.hyuk.MatchService;
import com.multicampus.matchcode.util.constants.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    @GetMapping("/addmatchmember")
    public String writeMatch(@PathVariable("matchid") Long matchId, Model model) {
        if (matchMemberService.isMatchExist(matchId)) {
            model.addAttribute("message", "참여중인 매치입니다.");
            model.addAttribute("searchUrl", "/match/list"); // 임시 mapping 주소
            return "hyem/message";
        } else {
            MatchMemberDTO matchMemberDTO = new MatchMemberDTO();
            model.addAttribute("recruit", matchMemberDTO);
            return "/match/list";
        }
    }

    // 매치원 추가
    @GetMapping("/post/addmatchmember2")
    public String addMatchMember(long matchId,
                                 @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = true) MemberDTO memberDTO, Model model) throws Exception {
       /* MatchMemberDTO matchMemberDTO = matchMemberService.matchMemberFind(id);
        long matchId = matchMemberDTO.getMatchId();*/
        /*long memberId = matchMemberDTO.getMemberId();*/
        long memberId = memberDTO.getId();
        matchMemberService.addMatchMember(matchId, memberId);
        model.addAttribute("message", "매치신청에 완료했습니다.");
        model.addAttribute("searchUrl", "/match/list");
        return "hyem/message";
    }

/*    @GetMapping("/applymatch")
    public String applyMatch(@PathVariable("no") Long matchId, Model model) {
        if (matchMemberService.isMatchExist(matchId)) {
            model.addAttribute("message", "참여중인 매치입니다.");
            model.addAttribute("searchUrl", "/match/list");
            return "hyem/message";
        } else {
            // 팀 멤버 추가 로직을 여기에 직접 구현
            MatchMemberDTO matchMemberDTO = new MatchMemberDTO();
            // matchMemberDTO 설정
            long memberId = matchMemberDTO.getMemberId();
            matchMemberService.addMatchMember(matchId, memberId);

            // 추가 후 메시지 추가
            model.addAttribute("message", "매치신청에 완료했습니다.");
            model.addAttribute("searchUrl", "/match/list");

            return "hyem/message";
        }
    }*/

}


