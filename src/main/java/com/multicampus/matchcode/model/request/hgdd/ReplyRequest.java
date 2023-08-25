package com.multicampus.matchcode.model.request.hgdd;

import com.multicampus.matchcode.model.entity.PostDTO;
import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReplyRequest {

    private PostDTO post;
    //private long userId;
    private String comment;
    private Timestamp createdDate;
}
