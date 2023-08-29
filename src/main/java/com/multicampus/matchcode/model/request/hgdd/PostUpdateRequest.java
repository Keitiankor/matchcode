package com.multicampus.matchcode.model.request.hgdd;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostUpdateRequest {

    private Timestamp createdDate;
    private Timestamp editedDate;
    private String title;
    private String content;
    private boolean privates;

}
