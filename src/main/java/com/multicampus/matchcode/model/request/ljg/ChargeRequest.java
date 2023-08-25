package com.multicampus.matchcode.model.request.ljg;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChargeRequest {

    private long userId;
    private int point;
    // getters, setters...
}
