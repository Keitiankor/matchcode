package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.*;
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


    //메인화면 member 이름과 point를 불러오기 위한 service메소드
    public MemberAndPointDTO getMemberAndPoint(long memberId) {
        Optional<MemberDTO> memberDTO = member.findById(memberId);

        if (memberDTO.isPresent()) {
            Optional<PointDTO> pointDTO = point.findByUserId(memberId);

            if (pointDTO.isPresent()) {
                return new MemberAndPointDTO(memberDTO.get(), pointDTO.get());
            } else {
                // If point info is not found, you can pass an empty list or handle it based on your requirement
                return new MemberAndPointDTO(memberDTO.get(), null);
            }
        } else {
            return null;
        }
    }


    //문제1) 그런데 '나의' 매치 기록을 가져와야 하는데...?

    //매치 히스토리 화면 내 종목에 맞는 기록을 불러오기 위한 service메소드
    public List<MatchDTO> getMatchesBySportsId(long sportsId) {
        return matches.findBySportsId(sportsId);
    }


    public ResultDTO getResultByMatchId(long matchId) {
        Optional<ResultDTO> odto = result.findByMatchIdAndUserId(matchId, (long)1);
        if(odto.isPresent()){
            return odto.get();
        }
        return null;
    }


    public MapDTO getMapByMatchId(long mapId) {
        return map.findById(mapId);
    }

    public MatchMemberDTO getMatchMemberByMatchId(long matchid) {
        return matchmember.findByMatchId(matchid);
    }


}
