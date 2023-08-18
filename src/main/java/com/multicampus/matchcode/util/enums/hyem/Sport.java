package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Sport {

    Bascetball("농구"), Futsal("풋살"), Badminton("배드민턴");

    private final String description;

    Sport(String description) {
        this.description = description;
    }
}
