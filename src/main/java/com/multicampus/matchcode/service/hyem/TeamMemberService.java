package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamMemberDTO;
import com.multicampus.matchcode.repository.ApplicationRepository;
import com.multicampus.matchcode.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamMemberService {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    // 팀장 추가
    public void addTeamLeader(long teamid, long mamberid) {
        TeamMemberDTO dto = TeamMemberDTO
                .builder()
                .memberId(mamberid)
                .teamId(teamid)
                .privilege(1) // 0 : 관리자, 1 : 팀장, 2 : 팀원
                .build();
        teamMemberRepository.save(dto);
    }

    // 팀원 추가
    public void addTeamMember() {
        TeamMemberDTO dto = TeamMemberDTO
                .builder()
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

    // 신청자 확인
    public boolean isApplicatedMember(long teamId, long memberId) {
        return applicationRepository.findByMemberIdAndTeamId(teamId, memberId);
    }

    /*// 팀 리스트 처리
    public Page<TeamDTO> teamList(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

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
