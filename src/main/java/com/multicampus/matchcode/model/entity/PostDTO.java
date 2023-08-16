package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity(name = "Post")
@Data
public class PostDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;
    private String title;
    private String content;

    @CreationTimestamp
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp editedDate;
    private int views;
    private int likes;
    private int declation;
    private int status;
}
