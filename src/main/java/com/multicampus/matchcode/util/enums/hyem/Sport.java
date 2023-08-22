package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Sport {

    농구(1), 풋살(2), 배드민턴(3);

    private final Integer count;

    Sport(Integer count) {
        this.count = count;
    }
}
