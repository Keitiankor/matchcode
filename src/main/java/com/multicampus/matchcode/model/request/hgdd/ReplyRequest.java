package com.multicampus.matchcode.model.request.hgdd;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class ReplyRequest {

    private String comment;

}
