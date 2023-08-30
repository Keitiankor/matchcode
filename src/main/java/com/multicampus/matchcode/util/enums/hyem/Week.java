package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Week {
    MON("월", 1),
    TUE("화", 2),
    WED("수", 3),
    THU("목", 4),
    FRI("금", 5),
    SAT("토", 6),
    SUN("일", 7);

    private final String description;
    private final Integer count;

    Week(String description, Integer count) {
        this.description = description;
        this.count = count;
    }

    public static String getDescriptionFromCount(Integer count) {
        for (Week week : Week.values()) {
            if (week.getCount().equals(count)) {
                return week.getDescription();
            }
        }
        return "Unknown";
    }
}
