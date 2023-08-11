package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Reply")
@Getter
public class ReplyDTO {

    @Id
    private long id;

    private long postId;
    private long userId;
    private String comment;
}
