package com.multicampus.matchcode.model.request.jun;

import com.multicampus.matchcode.model.entity.ReviewDTO;
import lombok.Data;

@Data
public class UpdateReviewRequest {

    long id;
    int rate;
    String comment;
    ReviewDTO dto;
}
