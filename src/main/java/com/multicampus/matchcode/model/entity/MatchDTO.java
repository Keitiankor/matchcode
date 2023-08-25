package com.multicampus.matchcode.model.entity;

import com.multicampus.matchcode.domain.Match;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

import lombok.*;

@Entity(name = "Match_")
@Getter
@AllArgsConstructor
@Builder
@ToString //객체가 가지고 있는 정보나 값을 문자열로 만들어 리턴하는 메서드
@NoArgsConstructor //인자없이 객체생성가능
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
