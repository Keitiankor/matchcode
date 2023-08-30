package com.multicampus.matchcode.model.request.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationRequest {

    private long id;
    private long userId;
    private TeamDTO teamId;
    private String introduction;
}
