package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<ApplicationDTO, Long> {
    Page<ApplicationDTO> findByTeamId(long teamId, Pageable pageable);

    @Query("select count(a.id) > 0 from Application a where a.teamId =:teamId and a.memberId =:memberId")
    public abstract boolean findByMemberIdAndTeamId(@Param("teamId") long teamId, @Param("memberId") long memberId);

/*    @Query("select a.id from Application a where a.memberId =: memberId")
    Optional<Long> findIdByMemberId(@Param("memberId") long memberId);*/
}
