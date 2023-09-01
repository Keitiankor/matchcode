package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Time {
    MORNING("아침", 1), AFTERNOON("낮", 2), EVENING("저녁", 3), NIGHT("심야", 4);

    private final String description;
    private final Integer count;

    Time(String description, Integer count) {
        this.description = description;
        this.count = count;
    }

    public static String getDescriptionFromCount(Integer count) {
        for (Time time : Time.values()) {
            if (time
                    .getCount()
                    .equals(count)) {
                return time.getDescription();
            }
        }
        return "Unknown";
    }
}
