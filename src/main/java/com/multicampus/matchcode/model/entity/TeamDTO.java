package com.multicampus.matchcode.model.entity;

        import jakarta.persistence.*;

        import java.sql.Timestamp;

        import lombok.*;
        import org.hibernate.annotations.CreationTimestamp;

@Entity(name = "Team_test")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TeamDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long mapId ;
    private long sportsId;
    private String teamName;
    private String uri;
    private String emblem;
    private long useWeek;
    private long useTime;
    private int teamRank ;
    private long averageAge;
    private long averageGender;
    @CreationTimestamp
    private Timestamp createdDate;
    private Integer status ;

    //체크박스 테스트코드
    //@Transientt
    //private List<String> selectedSports;
}
