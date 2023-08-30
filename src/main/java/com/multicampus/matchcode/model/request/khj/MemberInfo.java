package com.multicampus.matchcode.model.request.khj;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberInfo {

    private int communityLevel;
    private String name;
    private int point;
    private String teamName;
}
