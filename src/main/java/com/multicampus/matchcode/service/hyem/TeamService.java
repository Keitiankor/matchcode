package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.request.hyem.TeamCreateRequest;
import com.multicampus.matchcode.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    // 팀 생성
    public void save(TeamCreateRequest request) {
        TeamDTO dto = TeamDTO.builder()
                .sportsId(request.getSportsId())
                .teamName(request.getTeamName())
                .uri(request.getUri())
                .useWeek(request.getUseWeek())
                .useTime(request.getUseTime())
                .averageAge(request.getAverageAge())
                .averageGender(request.getAverageGender())
                .build();
        teamRepository.save(dto);
    }

    // 팀 리스트 처리
    public Page<TeamDTO> teamList(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    // 팀 정보 불러오기
    public TeamDTO teamView(Long id) {
        return teamRepository.findById(id).get();
    }

}
