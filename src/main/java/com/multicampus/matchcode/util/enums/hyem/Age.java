package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Age {

    teenager("10대"), TWENTIES("20대"), THIRTIES("30대"),
    FORTIES("40대"), FIFTIES("50대"), SIXTIES("60대");

    private final String description;

    Age(String description) {
        this.description = description;
    }
}
