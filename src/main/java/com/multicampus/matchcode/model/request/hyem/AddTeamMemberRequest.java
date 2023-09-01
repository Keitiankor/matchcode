package com.multicampus.matchcode.model.request.hyem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddTeamMemberRequest {
    private long memberId;
    private long teamId;
    private int privilege;
}
