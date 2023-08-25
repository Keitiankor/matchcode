package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import com.multicampus.matchcode.model.entity.ResultDTO;
import com.multicampus.matchcode.model.request.khj.MatchResult;
import com.multicampus.matchcode.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyHistoryServiceImpl implements MyHistoryService {

    // 기존의 필요한 의존성 주입
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

    @Override
    public List<MatchResult> getMatchResultsBySportsId(long sportsId) {
        List<MatchDTO> matches = getMatchesBySportsId(sportsId);
        List<MatchResult> matchResults = new ArrayList<>();

        for (MatchDTO match : matches) {
            ResultDTO result = getResultByMatchId(match.getId());
            if (result == null) {
                continue;
            }
            MapDTO map = getMapByMatchId(match.getMapId());
            MatchMemberDTO matchMember = getMatchMemberByMatchId(match.getId());
            MatchResult matchResult = new MatchResult(
                    match.getMatchDate(), // 여기서 데이터를 직접 가져옴
                    map.getName(), // 여기서 데이터를 직접 가져옴
                    result.getMyScore(),
                    result.getRivalScore()
            );
            matchResults.add(matchResult);
        }

        return matchResults;
    }

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
    //결과는 매치id뿐만 아니라, 유저id까지 가져오면서 '내' 매치기록들만 읽어오도록


    public MapDTO getMapByMatchId(long mapId) {
        return map.findById(mapId);
    }


    public MatchMemberDTO getMatchMemberByMatchId(long matchid) {
        return matchmember.findByMatchId(matchid);
    }
}
