package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Time {

    아침(1), 낮(10), 저녁(100),심야(1000);

    private final Integer count;

    Time(Integer count) {
        this.count = count;
    }
}
