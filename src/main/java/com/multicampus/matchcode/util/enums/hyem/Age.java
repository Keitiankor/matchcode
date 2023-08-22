package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Age {

    TEENAGE("10대", 1), TWENTIES("20대", 10), THIRTIES("30대", 100),
    FORTIES("40대", 1000), FIFTIES("50대", 10000), SIXTIES("60대", 100000);

    private final String description;
    private final Integer count;

    Age(String description, Integer count) {
        this.description = description;
        this.count = count;
    }
}
