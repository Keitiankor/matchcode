package com.multicampus.matchcode.model.request.khj;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingRequest {

    private int mmr;
    private String uri;
}
