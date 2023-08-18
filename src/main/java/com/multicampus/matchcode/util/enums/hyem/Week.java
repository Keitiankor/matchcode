package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Week {

    MON("월"), TUE("화"), WED("수"),
    THU("목"), FRI("금"), SAT("토"), SUN("일");

    private final String description;

    Week(String description) {
        this.description = description;
    }
}
