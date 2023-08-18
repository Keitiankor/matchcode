package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Time {

    MORNING("아침"), AFTERNOON("낮"), EVENING("저녁"),DAYBREAK("심야");

    private final String description;

    Time(String description) {
        this.description = description;
    }
}
