package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<ApplicationDTO, Long> {
    //List<ApplicationDTO> findByRecruit0Id(Long id);
}
