package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Gender {

    Female("여자"), Male("남자"), All("남녀모두");

    private final String description;

    Gender(String description) {
        this.description = description;
    }
}
