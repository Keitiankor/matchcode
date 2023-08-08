package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class ReplyDTO {

    private long id;
    private long postId;
    private long userId;
    private String comment;
}
