package com.multicampus.matchcode.model.request.khj;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResult {

    private Timestamp matchDate;
    private String name;
    private int myScore;
    private int rivalScore;
}
