package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import java.sql.Timestamp;
import lombok.Getter;

@Entity
@Getter
public class Post {

    private long id;
    private long userId;
    private String title;
    private String content;
    private Timestamp createdDate;
    private Timestamp editedDate;
    private int views;
    private int likes;
    private int declation;
    private int status;
}
