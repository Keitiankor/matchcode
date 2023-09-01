package com.multicampus.matchcode.model.request.hgdd;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class ReplyRequest {

    private long postId;
    private String comment;
    private Timestamp createdDate;
}
