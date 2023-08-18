package com.multicampus.matchcode.model.request.keitian;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistserRequest {

    long areaId;
    String account;
    String password;
    String name;
    String phone;
    String mailAddress;
    Timestamp birthday;
}
