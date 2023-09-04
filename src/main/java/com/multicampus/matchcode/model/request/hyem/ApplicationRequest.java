package com.multicampus.matchcode.model.request.hyem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationRequest {

    //private long id;
    private long memberId;
    private long teamId;
    private String introduction;
    //private int status;
}
