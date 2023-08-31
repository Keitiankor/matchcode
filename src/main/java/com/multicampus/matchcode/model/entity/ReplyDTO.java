package com.multicampus.matchcode.model.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "reply")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long postId;
    private long userId;

    private String comment;

    @CreationTimestamp
    private Timestamp createdDate;
}
