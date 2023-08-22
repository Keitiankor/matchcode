package com.multicampus.matchcode.model.request.hyem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamCreateRequest {

    private long sportsId;
    private String teamName;
    private String uri;
    private long useWeek;
    private long useTime;
    private long averageAge;
    private long averageGender;
}
