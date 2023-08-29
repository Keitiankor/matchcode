package com.multicampus.matchcode.model.request.hgdd;

public enum PostCategory {
    football,
    Badminton,
    basketball;

    public static PostCategory of(String category) {
        if (category.equalsIgnoreCase("football")) return PostCategory.football; else if (
            category.equalsIgnoreCase("Badminton")
        ) return PostCategory.Badminton; else if (
            category.equalsIgnoreCase("basketball")
        ) return PostCategory.basketball; else return null;
    }
}
