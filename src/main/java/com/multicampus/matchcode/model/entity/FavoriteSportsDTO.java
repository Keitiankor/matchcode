package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteSportsDTO {

    @Id
    private long id;

    private long userId;
    private long sportsId;
    private String Text;
}
