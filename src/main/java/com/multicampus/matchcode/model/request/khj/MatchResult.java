package com.multicampus.matchcode.model.request.khj;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import com.multicampus.matchcode.model.entity.ResultDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class MatchResult {

    private Timestamp matchDate;
    private String name;
    private int myScore;
    private int rivalScore;

    public MatchResult(MatchDTO match, ResultDTO result, MapDTO map, MatchMemberDTO matchMember) {
        this.matchDate = match.getMatchDate();
        this.name = map.getName();
        this.myScore = result.getMyScore();
        this.rivalScore = result.getRivalScore();
    }

    public long getId() {
        // getId() 메소드의 구현
        return 0L; // getId() 메소드 구현 내용에 맞게 수정
    }

    public long getMapId() {
        // getMapId() 메소드의 구현
        return 0L; // getMapId() 메소드 구현 내용에 맞게 수정
    }
}
