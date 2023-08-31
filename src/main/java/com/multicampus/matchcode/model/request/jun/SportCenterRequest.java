package com.multicampus.matchcode.model.request.jun;

import lombok.Data;

@Data
public class SportCenterRequest {

    long areaId;
    double longitude;
    double latitude;
    String SportCenterName;
    String managerName;
    String phone;
    String resistrationCode;
}
