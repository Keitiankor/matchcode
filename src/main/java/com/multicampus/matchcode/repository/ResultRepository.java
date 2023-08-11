package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultDTO, Long> {}
