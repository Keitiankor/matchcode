package com.multicampus.matchcode.model.entity;

import lombok.Getter;

@Getter
public class MatchHistoryDTO {
    private MatchDTO match;
    private ResultDTO result;
    private MapDTO map;
    private MatchMemberDTO matchMember;

    public MatchHistoryDTO(MatchDTO match, ResultDTO result, MapDTO map, MatchMemberDTO matchMember) {
        this.match = match;
        this.result = result;
        this.map = map;
        this.matchMember = matchMember;
    }

    // Getters and setters...
}
