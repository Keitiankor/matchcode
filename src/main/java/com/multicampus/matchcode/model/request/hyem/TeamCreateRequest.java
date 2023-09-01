package com.multicampus.matchcode.model.request.hyem;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeamCreateRequest {

    private List<Long> sportsId;
    private String teamName;
    private String uri;
    private List<Long> useWeek;
    private long useTime;
    private List<Long> averageAge;
    private long averageGender;
}
