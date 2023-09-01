package com.multicampus.matchcode.model.request.hgdd;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class PostUpdateRequest {

    private Timestamp createdDate;
    private String title;
    private String content;
    private boolean privates;

}
