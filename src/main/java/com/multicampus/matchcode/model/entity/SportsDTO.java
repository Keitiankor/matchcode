package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Sports")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportsDTO {

    @Id
    private long id;

    private String name;
}
