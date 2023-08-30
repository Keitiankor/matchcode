package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.model.request.khj.MatchResult;
import com.multicampus.matchcode.repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyHistoryService {

    @Autowired
    MemberRepository member;

    @Autowired
    PointRepository point;

    @Autowired
    MatchRepository matches;

    @Autowired
    MapRepository map;

    @Autowired
    RatingRepository rating;

    @Autowired
    ResultRepository result;

    @Autowired
    MatchMemberRepository matchmember;

    @Autowired
    EmblemRepository emblem;

    //스포츠 종목에 맞는 매치 기록
    public List<MatchResult> getMatchResultsBySportsId(long sportsId) {
        List<MatchDTO> matches = getMatchesBySportsId(sportsId);
        List<MatchResult> matchResults = new ArrayList<>();

        for (MatchDTO match : matches) {
            ResultDTO result = getResultByMatchId(match.getId());
            if (result == null) {
                continue;
            }
            MapDTO map = getMapByMatchId(match.getMapId());
            MatchResult matchResult = MatchResult
                .builder()
                .matchId(match.getId())
                .matchDate(match.getMatchDate())
                .name(map.getName())
                .myScore(result.getMyScore())
                .rivalScore(result.getRivalScore())
                .build();
            matchResults.add(matchResult);
        }

        return matchResults;
    }

    public RatingDTO getRatingBySportsIdAndUserId(long sportsId, long userId) {

        return rating.findBySportsIdAndUserId(sportsId, userId);
    }

    public EmblemDTO getEmblemById(long emblemId) {

        return emblem.findById(emblemId);
    }


    public List<MatchDTO> getMatchesBySportsId(long sportsId) {
        return matches.findBySportsId(sportsId);
    }

    //결과는 매치id뿐만 아니라, 유저id까지 가져오면서 '내' 매치기록들만 읽어오도록
    public ResultDTO getResultByMatchId(long matchId) {
        Optional<ResultDTO> odto = result.findByMatchIdAndUserId(matchId, (long) 1);
        //지금 임의로 userid가 1이지만, 나중에 세션값에서 받아올 것
        if (odto.isPresent()) {
            return odto.get();
        }
        return null;
    }

    public MapDTO getMapByMatchId(long mapId) {
        return map.findById(mapId).get();
    }


    //매치 id가 주어졌을때, 그 매치id로 경기 뛴 유저들 이름 찾기
    public List<String> getMembersByMatchId(long matchId) {
        List<MatchMemberDTO> matchMembers = matchmember.findAllByMatchId(matchId);
        List<String> memberNames = new ArrayList<>();

        for (MatchMemberDTO matchMember : matchMembers) {
            Optional<MemberDTO> members = member.findById(matchMember.getUserId());
            if (members.isPresent()) {
                memberNames.add(members.get().getName());
            }
        }

        return memberNames;
    }

    // 매너 점수증감 서비스는 지금은 무리인 것 같다.

//    public boolean increaseManner(long memberId) {
//        Optional<MemberDTO> memberOptional = member.findById(memberId);
//        if (memberOptional.isPresent()) {
//            MemberDTO member = memberOptional.get();
//            member.setMannerTemperture(member.getMannerTemperture() + 1); // 매너점수 증가
//            member.save(member); // 변경 내용 저장
//            return true;
//        }
//        return false;
//    }
//
//    public boolean decreaseManner(long memberId) {
//        Optional<MemberDTO> memberOptional = member.findById(memberId);
//        if (memberOptional.isPresent()) {
//            MemberDTO member = memberOptional.get();
//            member.setMannerTemperture(member.getMannerTemperture() - 1); // 매너점수 감소
//            member.save(member); // 변경 내용 저장
//            return true;
//        }
//        return false;
//    }
}
