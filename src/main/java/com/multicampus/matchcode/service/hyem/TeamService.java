package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.request.hyem.TeamCreateRequest;
import com.multicampus.matchcode.repository.RecruitRepository;
import com.multicampus.matchcode.repository.TeamMemberRepository;
import com.multicampus.matchcode.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    // 팀 생성
    public long createTeam(TeamCreateRequest request) {
        TeamDTO dto = TeamDTO
                .builder()
                .sportsId(request.getSportsId())
                .teamName(request.getTeamName())
                .uri(request.getUri())
                .useWeek(request.getUseWeek())
                .useTime(request.getUseTime())
                .averageAge(request.getAverageAge())
                .averageGender(request.getAverageGender())
                .build();
        teamRepository.save(dto);
        return dto.getId();
    }

    // 팀 리스트 처리
    public Page<TeamDTO> teamList(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    // 팀 정보 불러오기
    public TeamDTO teamView(long id) {
        return teamRepository
                .findById(id)
                .get();
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
    @Transactional
    public void teamDelete(long id) {
        if (recruitRepository
                .findByTeamId(id)
                .isPresent()) {
            recruitRepository.deleteRecruitsByTeamId(id);
        }
        teamRepository.deleteById(id);
    }

    // 모집글이 있는 팀 정보
    public Page<TeamDTO> teamViewWithRecruit(Pageable pageable) {
        return teamRepository.findAllWithRecruit(pageable);
    }
}
