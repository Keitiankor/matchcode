package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import java.sql.Timestamp;
import lombok.Getter;

@Entity
@Getter
public class MemberDTO {

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
