package com.multicampus.matchcode.model.request.hgdd;


import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class PostInsertRequest {

    private String title;
    private String content;
    private boolean privates;

}