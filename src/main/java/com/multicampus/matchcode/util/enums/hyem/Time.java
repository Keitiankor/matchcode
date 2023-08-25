package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Time {

    아침(1), 낮(2), 저녁(3),심야(4);

    private final Integer count;

    Time(Integer count) {
        this.count = count;
    }

    public static String getNameFromCount(Integer count) {
        for (Time time : Time.values()) {
            if (time.getCount().equals(count)) {
                return time.name();
            }
        }
        return "Unknown";
    }
}
