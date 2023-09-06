package com.multicampus.matchcode.model.request.hyuk;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
// Match : 실제 DB와 매칭될 클래스 (Entity Class)
// JPA에서는 프록시 생성을 위해 기본 생성자를 반드시 하나 생성해야 한다.
// 생성자 자동 생성 : NoArgsConstructor, AllArgsConstructor
// NoArgsConstructor : 객체 생성 시 초기 인자 없이 객체를 생성할 수 있다.

@Data
@Builder
public class Match {

/*    @Id // PK Field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙
    private long id;*/

    private Long mapId;
    private Long sportsId;
    private String content;
    private Timestamp matchDate;
    private Timestamp createdDate;
    private Timestamp expireDate;
    private Long status;
/*    private int restrictionMinRate;
    private int restrictionMaxRate;*/
}
