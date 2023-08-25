package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ChargeHistoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeHistoryRepository extends JpaRepository<ChargeHistoryDTO, Long> {}
