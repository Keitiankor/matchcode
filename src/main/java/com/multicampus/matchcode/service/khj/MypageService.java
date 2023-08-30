package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.model.request.khj.MemberInfoRequest;
import com.multicampus.matchcode.repository.*;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    @Autowired
    MemberRepository member;

    @Autowired
    PointRepository point;

    @Autowired
    MatchRepository matches;

    @Autowired
    MapRepository map;

    @Autowired
    ResultRepository result;

    @Autowired
    MatchMemberRepository matchmember;

    @Autowired
    TeamMemberRepository teammember;

    @Autowired
    TeamRepository team;

    //메인화면 member 이름과 point를 불러오기 위한 service메소드
    public MemberInfoRequest getMemberInfo(long memberId) {
        Optional<MemberDTO> memberDTO = member.findById(memberId);
        Optional<List<PointDTO>> pointDTO = point.findAllByUserId(memberId);
        Optional<TeamMemberDTO> teamMemberDTO = teammember.findByUserId(memberId);
        System.out.println(team.findById((long)1).toString());
        Optional<TeamDTO> teamDTO =
                teamMemberDTO.isPresent()
                ? team.findById(teamMemberDTO.get().getTeamId())
                : Optional.empty();

        String teamName = teamDTO.isPresent()
                ? teamDTO.get().getTeamName()
                : "현재 소속된 팀이 없습니다";
        int sum = 0;
        if(pointDTO.isPresent()){
            for (PointDTO dto: pointDTO.get()) {
                sum += dto.getPoint();
            }
        }

        return MemberInfoRequest
                .builder()
                .name(memberDTO.get().getName())
                .point(sum)
                .communityLevel(memberDTO.get().getCommunityLevel())
                .teamName(teamName)
                .build();
    }

    public MemberDTO getMemberById(long id){
        Optional<MemberDTO> odto = member.findById(id);
        //지금 임의로 userid가 1이지만, 나중에 세션값에서 받아올 것
        if (odto.isPresent()) {
            return odto.get();
        }
        return null;
    }
}
