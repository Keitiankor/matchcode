package com.multicampus.matchcode.model.request.hyem;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddTeamMemberRequest {

    private int privilege;
}
