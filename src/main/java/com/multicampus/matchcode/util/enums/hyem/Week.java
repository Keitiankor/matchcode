package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Week {

    월(1), 화(10), 수(100),
    목(1000), 금(10000), 토(100000), 일(1000000);

    private final Integer count;

    Week(Integer count) {
        this.count = count;
    }
}
