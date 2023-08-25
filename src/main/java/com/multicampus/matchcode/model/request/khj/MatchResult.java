package com.multicampus.matchcode.model.request.khj;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.MatchMemberDTO;
import com.multicampus.matchcode.model.entity.ResultDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class MatchResult {

    private Timestamp matchDate;
    private String name;
    private int myScore;
    private int rivalScore;

}
