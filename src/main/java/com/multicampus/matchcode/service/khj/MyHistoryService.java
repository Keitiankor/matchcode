package com.multicampus.matchcode.service.khj;

import com.multicampus.matchcode.model.request.khj.MatchResult;

import java.util.List;

public interface MyHistoryService {
    List<MatchResult> getMatchResultsBySportsId(long sportsId);
}
