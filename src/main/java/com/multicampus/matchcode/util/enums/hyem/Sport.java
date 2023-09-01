package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Sport {
    BASKETBALL("농구", 1), FUTSAL("풋살", 2), BADMINTON("배드민턴", 3);

    private final String description;
    private final Integer count;

    Sport(String description, Integer count) {
        this.description = description;
        this.count = count;
    }

    public static String getDescriptionFromCount(Integer count) {
        for (Sport sport : Sport.values()) {
            if (sport.getCount().equals(count)) {
                return sport.getDescription();
            }
        }
        return "Unknown";
    }
}
