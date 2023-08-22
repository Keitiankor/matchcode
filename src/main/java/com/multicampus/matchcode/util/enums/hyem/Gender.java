package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Gender {

    여자(1), 남자(2), 남녀모두(3);

    private final Integer count;

    Gender(Integer count) {
        this.count = count;
    }
}
