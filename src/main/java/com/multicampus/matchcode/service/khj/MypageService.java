package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.MemberDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.entity.TeamMemberDTO;
import com.multicampus.matchcode.model.request.khj.MemberInfoRequest;
import com.multicampus.matchcode.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //메인화면에 이름,포인트,활동레벨,팀네임을 가져오기 위한 service메소드
    public MemberInfoRequest getMemberInfo(long memberId) {
        Optional<MemberDTO> memberDTO = member.findById(memberId);
        Optional<List<PointDTO>> pointDTO = point.findAllByMemberId(memberId);
        Optional<TeamMemberDTO> teamMemberDTO = teammember.findByMemberId(memberId);
        System.out.println(team
                                   .findById((long) 1)
                                   .toString());
        Optional<TeamDTO> teamDTO = teamMemberDTO.isPresent()
                                    ? team.findById(teamMemberDTO
                                                            .get()
                                                            .getTeamId())
                                    : Optional.empty();

        String teamName = teamDTO.isPresent()
                          ? teamDTO
                                  .get()
                                  .getTeamName()
                          : "현재 소속된 팀이 없습니다";
        int sum = 0; // 이건 pointDTO가 point사용 로그를 남기는 DTO라서, 이전 기록들 전부 증감
        if (pointDTO.isPresent()) {
            for (PointDTO dto : pointDTO.get()) {
                sum += dto.getPoint();
            }
        }

        return MemberInfoRequest
                .builder()
                .name(memberDTO
                              .get()
                              .getName())
                .point(sum)
                .communityLevel(memberDTO
                                        .get()
                                        .getCommunityLevel())
                .teamName(teamName)
                .build();
    }

    //'개인정보' 탭에서 쓰기 위해 현재 로그인한 사람의 MemberDTO 객체를 가져오는 메서드
    public MemberDTO getMemberById(long id) {
        Optional<MemberDTO> odto = member.findById(id);
        //지금 임의로 memberid가 1이지만, 나중에 세션값에서 받아올 것
        if (odto.isPresent()) {
            return odto.get();
        }
        return null;
    }
}
