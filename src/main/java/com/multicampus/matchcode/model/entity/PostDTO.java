package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long memberId;
    private String title;
    private String content;
    private String writer;

    @CreationTimestamp
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp editedDate;

    private int views;
    private int likes;
    private boolean privates;
    private int status;
    private int declation;
}
