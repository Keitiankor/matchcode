package com.multicampus.matchcode.model.request.hyem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamMemberRequest {

    private long teamId;
    private long memberId;
}
