package com.multicampus.matchcode.service.hyuk;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import com.multicampus.matchcode.model.entity.TeamMemberDTO;
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
        return matchMemberRepository
                .findByMatchId(matchId)
                .isPresent();
    }
    // 가입신청 정보
    public MatchMemberDTO matchMemberFind(long id) {
        return matchMemberRepository
                .findById(id)
                .get();
    }
    // 팀장 추가
    public void addMatchLeader(long matchId, long memberId) {
        MatchMemberDTO dto = MatchMemberDTO
                .builder()
                .memberId(memberId)
                .matchId(matchId)
                .privilege(1) // 0 : 관리자, 1 : 팀장, 2 : 팀원
                .build();
        matchMemberRepository.save(dto);
    }

    // 팀원 추가
    public void addMatchMember(long matchId, long memberId) {
        MatchMemberDTO dto = MatchMemberDTO
                .builder()
                .matchId(matchId)
                .memberId(memberId)
                .privilege(2) // 0 : 관리자, 1 : 팀장, 2 : 팀원
                .build();
        matchMemberRepository.save(dto);


}
}
