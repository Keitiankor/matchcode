package com.multicampus.matchcode.model.request.keitian;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    private String account;
    private String password;
}
