package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    @Id
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
