package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.PointDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<PointDTO, Long> {
    public Optional<PointDTO> findByUserId(long memberId);//userid가 있는 pointDTO를 가져와서 담는 메소드<<?
    //근데 memberId여야 하는거 아닌가...?
}
