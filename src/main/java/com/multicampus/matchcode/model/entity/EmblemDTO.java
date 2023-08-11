package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "Emblem")
@Getter
public class EmblemDTO {

    @Id
    private long id;

    private String uri;
}
