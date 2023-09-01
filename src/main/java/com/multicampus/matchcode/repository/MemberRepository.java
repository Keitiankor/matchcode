package com.multicampus.matchcode.repository;

import com.multicampus.matchcode.model.entity.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberDTO, Long> {
    Optional<MemberDTO> findByAccount(String account);

    @Modifying
    @Query(value = "update Member m set m.password = ?2 where m.id = ?1")
    void updatePasswordById(long id, String password);

}
