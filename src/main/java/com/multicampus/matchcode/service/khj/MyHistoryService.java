package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.*;
import com.multicampus.matchcode.model.request.khj.MatchResultRequest;
import com.multicampus.matchcode.model.request.khj.RatingRequest;
import com.multicampus.matchcode.repository.*;
import com.multicampus.matchcode.util.constants.SessionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @ModelAttribute("memberId")
    public long getMemberId(@SessionAttribute(name = SessionConstant.MEMBER_DTO) MemberDTO loggedInMember) {
        return loggedInMember.getId();
    }

    //스포츠 종목에 맞는 매치 기록
    public List<MatchResultRequest> getMatchResultsBySportsIdAndMemberId(long sportsId, long memberId) {
        List<MatchDTO> matches = getMatchesBySportsId(sportsId);
        List<MatchResultRequest> matchResults = new ArrayList<>();
        for (MatchDTO match : matches) {
            ResultDTO result = getResultByMatchId(match.getId(), memberId);
            if (result == null) {
                continue;
            }
            MapDTO map = getMapByMatchId(match.getMapId());
            MatchResultRequest matchResult = MatchResultRequest
                    .builder()
                    .matchId(match.getId())
                    .matchDate(match.getMatchDate())
                    .name(map.getSportCenterName())
                    .myScore(result.getMyScore())
                    .rivalScore(result.getRivalScore())
                    .build();
            matchResults.add(matchResult);
        }
        return matchResults;
    }

    public RatingDTO getRatingBySportsIdAndMemberId(long sportsId, long memberId) {
        Optional<RatingDTO> ratingDTO = rating.findBySportsIdAndMemberId(sportsId, memberId);
        if (ratingDTO.isPresent()) {
            return ratingDTO.get();
        }
        return null;
    }

    public EmblemDTO getEmblemById(long emblemId) {
        Optional<EmblemDTO> odto = emblem.findById(emblemId);
        if (odto.isPresent()) {
            return odto.get();
        }
        return null;
    }

    public List<MatchDTO> getMatchesBySportsId(long sportsId) {
        return matches.findBySportsId(sportsId);
    }

    //결과는 매치id뿐만 아니라, 유저id까지 가져오면서 '내' 매치기록들만 읽어오도록
    public ResultDTO getResultByMatchId(long matchId, long memberId) {
        Optional<ResultDTO> odto = result.findByMatchIdAndMemberId(matchId, memberId);
        if (odto.isPresent()) {
            return odto.get();
        }
        return null;
    }

    public MapDTO getMapByMatchId(long mapId) {
        return map
                .findById(mapId)
                .get();
    }

    //매치 id가 주어졌을때, 그 매치id로 경기 뛴 유저들 이름 찾기
    public List<String> getMembersByMatchId(long matchId) {
        List<MatchMemberDTO> matchMembers = matchmember.findAllByMatchId(matchId);
        List<String> memberNames = new ArrayList<>();
        for (MatchMemberDTO matchMember : matchMembers) {
            Optional<MemberDTO> members = member.findById(matchMember.getMemberId());
            if (members.isPresent()) {
                memberNames.add(members
                                        .get()
                                        .getName());
            }
        }
        return memberNames;
    }
}
