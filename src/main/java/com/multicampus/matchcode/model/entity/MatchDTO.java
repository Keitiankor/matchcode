package com.multicampus.matchcode.model.entity;

import com.multicampus.matchcode.domain.Match;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
<<<<<<< HEAD

import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
>>>>>>> 8250e1a08ae181814a81fcfb42750986470add50

@Entity(name = "Match_") //match가 예악어라서 그냥 두면 충돌이 남(테이블 안생김 ㅠ)
@Getter
<<<<<<< HEAD
@AllArgsConstructor
@Builder
@ToString //객체가 가지고 있는 정보나 값을 문자열로 만들어 리턴하는 메서드
@NoArgsConstructor //인자없이 객체생성가능
=======
@NoArgsConstructor
@AllArgsConstructor
@Builder
>>>>>>> 8250e1a08ae181814a81fcfb42750986470add50
public class MatchDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long mapId;
    private long sportsId;
    private Timestamp matchDate;
    private Timestamp createdDate;
    private Timestamp expireDate;
    private int restrictionMinRate;
    private int restrictionMaxRate;
    private int status;

    public Match toEntity() {
        Match match = Match.builder()
                .id(id)
                .mapId(mapId)
                .sportsId(sportsId)
                .matchDate(matchDate)
                .createdDate(createdDate)
                .expireDate(expireDate)
                .restrictionMinRate(restrictionMinRate)
                .restrictionMaxRate(restrictionMaxRate)
                .status(status)
                .build();
        return match;
    }

}
