package com.multicampus.matchcode.service.hyuk;

import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import com.multicampus.matchcode.model.request.hyuk.Match;
import com.multicampus.matchcode.model.request.hyuk.MatchData;
import com.multicampus.matchcode.repository.MatchMemberRepository;
import com.multicampus.matchcode.repository.MatchRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service

public class MatchMemberService {

    @Autowired
    private MatchMemberRepository matchMemberRepository;

    @Autowired
    private MatchRepository matchRepository;
    // 모집중 여부 조회 -> 아래
    public boolean isMatchExist(long matchId) {
        return MatchMemberRepository
                .findByMatchId(matchId)
                .isPresent();
    }


}
