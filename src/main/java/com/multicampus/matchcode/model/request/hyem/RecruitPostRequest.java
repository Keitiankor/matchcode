package com.multicampus.matchcode.model.request.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class RecruitPostRequest {

    private long id;
    private TeamDTO teamId;
    private String content;
    private Timestamp createdDate;
}
