package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.TeamMemberDTO;
import com.multicampus.matchcode.model.request.hyem.TeamMemberInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMemberDTO, Long> {
    Optional<TeamMemberDTO> findByMemberId(long memberId);

    Page<TeamMemberDTO> findAllByTeamId(Pageable pageable, long TeamId);

    boolean existsByMemberId(long memberId);

    @Query("select m.privilege from TeamMember m where m.teamId = :teamId and m.memberId =:memberId")
    public abstract Integer findPrivilegeByTeamIdAndMemberId(
            @Param("teamId") long teamId,
            @Param("memberId") long memberId
    );

    @Query(value = "SELECT tm.id, m.name, tm.privilege" +
            " FROM TeamMember tm " +
            " LEFT JOIN Member m" +
            " ON tm.memberId = m.id" +
            " WHERE tm.teamId =:teamId")
    Page<Object[]> getMemberNameWithTeamId(Pageable pageable, @Param("teamId") long teamId);

    @Query(value = "select t.teamName, m.name, m.mannerTemperture " +
            "from TeamMember tm " +
            "inner join Member m " +
            "inner join TeamTest t " +
            "on tm.memberId = m.id and tm.teamId = t.id " +
            "where tm.teamId =:teamId and tm.id =:teamMemberId")
    Object getTeamMemberInfo(@Param("teamId") long teamId, @Param("teamMemberId") long teamMemberId);

    @Query(value = "select tm.id from TeamMember tm inner join Member m " +
    "on tm.memberId = m.id and tm.teamId =:teamId " +
    "where m.name =:name")
    long getIdByName(@Param("teamId") long teamId, @Param("name") String name);
}
