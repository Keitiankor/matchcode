package com.multicampus.matchcode.model.request.khj;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberUpdateRequest {
    private String name;
    private String phone;
    private String mailAddress;
}
