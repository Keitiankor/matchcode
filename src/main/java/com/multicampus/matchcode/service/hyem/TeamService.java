package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
