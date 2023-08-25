package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import lombok.*;

@Entity(name = "Member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long areaId;
    private String account;
    private String password;
    private String name;
    private String phone;
    private String mailAddress;
    private Timestamp birthday;
    private Timestamp createdDate;
    private Timestamp deletedDate;
    private Timestamp lastLogin;
    private float mannerTemperture;
    private int communityLevel;
    private int communityExp;
}
