package com.multicampus.matchcode.controller.hyuk;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.repository.MatchMemberRepository;
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

/*    @GetMapping("/post/addmatchmember44")
    public String writeMatch(
            long matchId,
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = true) MemberDTO memberDTO,
            Model model) throws Exception {
        if (matchMemberService.isMatchExist(matchId)) {
            model.addAttribute("message", "참여중인 매치입니다.");
            model.addAttribute("searchUrl", "/match/list"); // 임시 mapping 주소
            return "match/matchmessage";
        } else {
            return "/match/list";
        }
    }*/

    // 매치원 추가
    @GetMapping("/post/addmatchmember2")
    public String addMatchMember(
            long matchId,
            @SessionAttribute(name = SessionConstant.MEMBER_DTO, required = true) MemberDTO memberDTO,
            Model model) throws Exception {
        long memberId = memberDTO.getId();

        // 매치와 맴버가 이미 연결되어 있는지 확인
        if (matchMemberService.isMatchExistWithMemberId(matchId, memberId)) {
            model.addAttribute("message", "이미 참여 중인 매치입니다.");
            model.addAttribute("searchUrl", "/match/list");
            return "match/matchmessage";
        } else {
            // 연결되어 있지 않으면 매치 멤버 추가 로직을 수행
            matchMemberService.addMatchMember(matchId, memberId);
            model.addAttribute("message", "매치신청에 완료했습니다.");
            model.addAttribute("searchUrl", "/match/list");
            return "match/matchmessage";


        }
    }
}


