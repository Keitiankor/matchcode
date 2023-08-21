package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamDTO save(TeamDTO teamDTO) {
        return teamRepository.save(teamDTO);
    }

    public TeamDTO create(String teamName, String uri, String sportId
            ,String averageGender, String averageAge, String useWeek, String useTime) {
        return TeamDTO.builder()
                .teamName(teamName)
                .uri(uri)
                .sportsId(sportId)
                .averageGender(averageGender)
                .averageAge(averageAge)
                .useWeek(useWeek)
                .useTime(useTime)
                .build();
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
