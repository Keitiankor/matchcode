package com.multicampus.matchcode.model.request.jun;

import lombok.Data;

@Data
public class UpdateReviewRequest {

    long id;
    int rate;
    String comment;
}
