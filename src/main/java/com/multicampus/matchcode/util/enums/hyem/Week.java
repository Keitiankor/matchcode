package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Week {
    월(1),
    화(2),
    수(3),
    목(4),
    금(5),
    토(6),
    일(7);

    private final Integer count;

    Week(Integer count) {
        this.count = count;
    }

    public static String getNameFromCount(Integer count) {
        for (Week week : Week.values()) {
            if (week.getCount().equals(count)) {
                return week.name();
            }
        }
        return "Unknown";
    }
}
