package com.multicampus.matchcode.model.request.hyem;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class RecruitPostRequest {

    private long id;
    private long teamId;
    private String content;
    private Timestamp createdDate;
}
