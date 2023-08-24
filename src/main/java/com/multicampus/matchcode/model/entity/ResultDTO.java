package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Result")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO {

    @Id
    private long id;

    private long matchId;
    private long userId;
    //private long sportsId;
    private int status; // 단순히 '상태'만을 표현. 아직 어떤 값이 들어올지 명확하진 않음
    private int myScore; // 경기에서 우리팀이 얻은 점수
    private int rivalScore; // 경기에서 상대팀이 얻은 점수
    
}
