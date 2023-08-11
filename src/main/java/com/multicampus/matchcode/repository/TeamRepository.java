package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.TeamDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamDTO, Long> {}
