package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Age {
    TEENAGE("10대", 1), TWENTIES("20대", 2), THIRTIES("30대", 3), FORTIES("40대", 4), FIFTIES("50대", 5), SIXTIES("60대", 6);

    private final String description;
    private final Integer count;

    Age(String description, Integer count) {
        this.description = description;
        this.count = count;
    }

    public static String getDescriptionFromCount(Integer count) {
        for (Age age : Age.values()) {
            if (age.getCount().equals(count)) {
                return age.getDescription();
            }
        }
        return "Unknown";
    }
}
