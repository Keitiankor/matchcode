package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.entity.TeamMemberDTO;
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

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    // 팀 정보 불러오기
    public TeamCreateRequest getMemberInfo(long memberId) {
        Optional<TeamMemberDTO> teamMemberDTO = teamMemberRepository.findByMemberId(memberId);

        System.out.println(teamRepository
                                   .findById((long) 1)
                                   .toString());
        Optional<TeamDTO> teamDTO = teamMemberDTO.isPresent()
                                    ? teamRepository.findById(teamMemberDTO
                                                            .get()
                                                            .getTeamId())
                                    : Optional.empty();

        String teamName = teamDTO.isPresent()
                          ? teamDTO
                                  .get()
                                  .getTeamName()
                          : "현재 소속된 팀이 없습니다";

        return TeamCreateRequest
                .builder()
                .sportsId(teamDTO
                              .get()
                              .getSportsId())
                .teamName(teamName)
                .uri(teamDTO.get().getUri())
                .useWeek(teamDTO.get().getUseWeek())
                .useTime(teamDTO.get().getUseTime())
                .averageAge(teamDTO.get().getAverageAge())
                .averageGender(teamDTO.get().getAverageGender())
                .build();
    }

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
        recruitRepository.deleteRecruitsByTeamId(id);
        teamMemberRepository.deleteByTeamId(id);
    }

    // 모집글이 있는 팀 정보
    public Page<TeamDTO> teamViewWithRecruit(Pageable pageable) {
        return teamRepository.findAllWithRecruit(pageable);
    }

    // 팀 uri 가져오기
    public String getTeamUri(long teamId) {
        return teamRepository.findUriById(teamId);
    }
}
