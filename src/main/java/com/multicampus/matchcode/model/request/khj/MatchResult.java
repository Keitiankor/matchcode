package com.multicampus.matchcode.model.request.khj;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchResult {

    private String name;
    private int myScore; // 경기에서 우리팀이 얻은 점수
    private int rivalScore; // 경기에서 상대팀이 얻은 점수
}
