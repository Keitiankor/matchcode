package com.multicampus.matchcode.model.request.hyuk;

import lombok.Data;

@Data
public class MatchMemberRequest {

    private long matchId;

    private long mapId;
    private long sportsId;
}
