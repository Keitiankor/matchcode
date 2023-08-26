package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import com.multicampus.matchcode.model.entity.ResultDTO;
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
    ResultRepository result;

    @Autowired
    MatchMemberRepository matchmember;

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
                .matchDate(match.getMatchDate())
                .name(map.getName())
                .myScore(result.getMyScore())
                .rivalScore(result.getRivalScore())
                .build();
            matchResults.add(matchResult);
        }

        return matchResults;
    }

    public List<MatchDTO> getMatchesBySportsId(long sportsId) {
        return matches.findBySportsId(sportsId); // 실제로 매치 리스트를 가져오는 로직을 구현해야 함
    }

    public ResultDTO getResultByMatchId(long matchId) {
        Optional<ResultDTO> odto = result.findByMatchIdAndUserId(matchId, (long) 1);
        if (odto.isPresent()) {
            return odto.get();
        }
        return null;
    }

    //결과는 매치id뿐만 아니라, 유저id까지 가져오면서 '내' 매치기록들만 읽어오도록

    public MapDTO getMapByMatchId(long mapId) {
        return map.findById(mapId).get();
    }

    public MatchMemberDTO getMatchMemberByMatchId(long matchid) {
        return matchmember.findByMatchId(matchid);
    }
}
