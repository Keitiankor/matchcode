package com.multicampus.matchcode.util.enums.hyem;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남자", 1), FEMALE("여자", 2), ALL("남녀모두", 3);

    private final String description;
    private final Integer count;

    Gender(String description, Integer count) {
        this.description = description;
        this.count = count;
    }

    public static String getDescriptionFromCount(Integer count) {
        for (Gender gender : Gender.values()) {
            if (gender.getCount()
                      .equals(count)) {
                return gender.getDescription();
            }
        }
        return "Unknown";
    }
}
