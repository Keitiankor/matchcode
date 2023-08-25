package com.multicampus.matchcode.repository;


import com.multicampus.matchcode.model.entity.ChargeHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeHistoryRepository extends JpaRepository<ChargeHistoryDTO, Long> {

    }
