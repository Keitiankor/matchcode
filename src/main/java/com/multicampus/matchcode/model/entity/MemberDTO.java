package com.multicampus.matchcode.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity(name = "Member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String account;
    private String password; //수정가능
    private String name;
    private String phone; //수정가능
    private String mailAddress;
    private Timestamp birthday;
    @CreationTimestamp
    private Timestamp createdDate;
    private Timestamp deletedDate;
    private Timestamp lastLogin;
    private float mannerTemperture;
    private int communityLevel;
    private int communityExp;
}