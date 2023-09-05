package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamMemberDTO;
import com.multicampus.matchcode.model.request.hyem.TeamMemberInfo;
import com.multicampus.matchcode.repository.ApplicationRepository;
import com.multicampus.matchcode.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    // 팀장 추가
    public void addTeamLeader(long teamId, long memberId) {
        TeamMemberDTO dto = TeamMemberDTO
                .builder()
                .memberId(memberId)
                .teamId(teamId)
                .privilege(1) // 0 : 관리자, 1 : 팀장, 2 : 팀원
                .build();
        teamMemberRepository.save(dto);
    }

    // 팀원 추가
    public void addTeamMember(long teamId, long memberId) {
        TeamMemberDTO dto = TeamMemberDTO
                .builder()
                .teamId(teamId)
                .memberId(memberId)
                .privilege(2) // 0 : 관리자, 1 : 팀장, 2 : 팀원
                .build();
        teamMemberRepository.save(dto);
    }

    // 팀원 확인
    public boolean isTeamMember(long memberId) {
        return teamMemberRepository.existsByMemberId(memberId);
    }

    // 팀장 권한 확인
    public Integer isTeamLeader(long teamId, long memberId) {
        Integer privilege = teamMemberRepository.findPrivilegeByTeamIdAndMemberId(teamId, memberId);
        if (privilege == null) {
            return 0;
        }
        return privilege;
    }

    // 권한 확인
    public Integer getPrivilege(long memberId) {
        return teamMemberRepository.findByMemberId(memberId).get().getPrivilege();
    }

    // 가입된 팀 id 확인
    public long getTeamId(long memberId) {
        return teamMemberRepository.findByMemberId(memberId).get().getTeamId();
    }

    // 신청자 확인
    public boolean isApplicatedMember(long teamId, long memberId) {
        return applicationRepository.findByMemberIdAndTeamId(teamId, memberId);
    }

    public TeamMemberDTO teamMemberInfo(long memberId) {
        return teamMemberRepository.findByMemberId(memberId).orElse(null);
    }

    // 팀원 리스트
    public Page<TeamMemberDTO> teamMemberList(Pageable pageable) {
        return teamMemberRepository.findAll(pageable);
    }

    // 팀별 팀원 리스트
    public Page<TeamMemberDTO> teamMemberList2(Pageable pageable, long teamId) {
        return teamMemberRepository.findAllByTeamId(pageable, teamId);
    }

    // 멤버 리스트 정보 가져오기
    public Page<Object[]> teamMemberList3(Pageable pageable, long teamId) {
        return teamMemberRepository.getMemberNameWithTeamId(pageable, teamId);
    }

    // 멤버 상세 정보 가져오기
    public Objects getMemberInfo(long teamId, long teamMemberId) {
        return (Objects) teamMemberRepository.getTeamMemberInfo(teamId, teamMemberId);
    }

    public long getTeamMemberName(long teamId, String name) {
        return teamMemberRepository.getIdByName(teamId, name);
    }

    // 팀원 수
    public long getTeamMemberCount(long teamId) {
        return teamMemberRepository.countByTeamId(teamId);
    }
     /*
    // 팀 정보 불러오기
    public TeamDTO teamView(long id) {
        return teamRepository.findById(id).get();
    }

    // 팀 정보 수정
    public TeamDTO teamUpdate(long id, TeamCreateRequest request) {
        TeamDTO existingTeam = teamRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        TeamDTO updatedTeam = TeamDTO
            .builder()
            .id(existingTeam.getId())
            .sportsId(request.getSportsId())
            .teamName(request.getTeamName())
            .uri(request.getUri())
            .useWeek(request.getUseWeek())
            .useTime(request.getUseTime())
            .averageAge(request.getAverageAge())
            .averageGender(request.getAverageGender())
            .build();

        return teamRepository.save(updatedTeam);
    }

    // 팀 삭제
    public void teamDelete(long id) {
        teamRepository.deleteById(id);
    }

    // 데이터 저장 비트 연산
    public long saveValue(List<Long> selectedColumns) {
        int selectedValues = 0;
        for(Long id : selectedColumns) {
            selectedValues |= 1 << id.intValue();
        }
        return selectedValues;
    }

    // 비트 데이터 반환
    public List<Long> extractValue(long selectedValues) {
        List<Long> extractedValues = new ArrayList<>();
        int index = 0;

        while (selectedValues > 0) {
            if ((selectedValues & 1) == 1) {
                extractedValues.add((long) index);
            }
            selectedValues >>= 1;
            index++;
        }
        return extractedValues;
    }*/
}
