package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.TeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<TeamDTO, Long> {
    @Query("select t from Recruit r Left outer join TeamTest t on t.id = r.teamId")
    Page<TeamDTO> findAllWithRecruit(Pageable pageable);


}
