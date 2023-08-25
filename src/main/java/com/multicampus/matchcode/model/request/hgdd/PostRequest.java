package com.multicampus.matchcode.model.request.hgdd;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostRequest {

    private Timestamp createdDate;
    private Timestamp editedDate;
    private long userId;
    private String title;
    private String content;
    private int views;
    private int likes;
    private int declation;
    private int status;
}
