package com.multicampus.matchcode.model.request.khj;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class MatchResultRequest {
    private long matchId;
    private Timestamp matchDate;
    private String name;
    private int myScore;
    private int rivalScore;
}
