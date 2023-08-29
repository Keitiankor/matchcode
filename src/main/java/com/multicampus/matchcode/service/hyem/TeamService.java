package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.request.hyem.TeamCreateRequest;
import com.multicampus.matchcode.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    // 팀 생성
    public void save(TeamCreateRequest request) {
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
    }

    // 팀 리스트 처리
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
    }
}
