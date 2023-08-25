package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Gender {

    여자(1), 남자(2), 남녀모두(3);

    private final Integer count;

    Gender(Integer count) {
        this.count = count;
    }

    public static String getNameFromCount(Integer count) {
        for (Gender gender : Gender.values()) {
            if (gender.getCount().equals(count)) {
                return gender.name();
            }
        }
        return "Unknown"; // 또는 다른 디폴트 값을 설정할 수 있습니다.
    }
}
