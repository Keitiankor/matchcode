package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.RecruitDTO;
import com.multicampus.matchcode.model.entity.TeamDTO;
import com.multicampus.matchcode.model.request.hyem.RecruitListRequest;
import com.multicampus.matchcode.model.request.hyem.RecruitPostRequest;
import com.multicampus.matchcode.repository.RecruitRepository;
import com.multicampus.matchcode.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitService {

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private TeamRepository teamRepository;

    // 모집글 작성
    public Long save(RecruitPostRequest request) {
        RecruitDTO dto = RecruitDTO
                .builder()
                .teamId(request.getTeamId())
                .content(request.getContent())
                .status(1)
                .build();

        recruitRepository.save(dto);
        return request.getId();
    }

    // 모집글 리스트 처리
    public Page<RecruitListRequest> getRecruitsWithTeamNames() {

        List<RecruitDTO> recruitDTOS = recruitRepository.findAll();

        List<RecruitListRequest> recruitList = new ArrayList<>();
        for (RecruitDTO recruitDTO : recruitDTOS) {
            long teamId = recruitDTO.getTeamId();
            Optional<TeamDTO> oTeamDTO = teamRepository.findById(teamId);
            recruitList.add(RecruitListRequest
                    .builder()
                    .teamName(oTeamDTO.map(TeamDTO::getTeamName)
                            .orElse(null))
                    .recruitId(recruitDTO.getId())
                    .build());
        }

        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()),recruitList.size());

        Page<RecruitListRequest> recruitPage = new PageImpl<>(recruitList.subList(start,end), pageRequest, recruitList.size());

        return recruitPage;
    }

    public RecruitDTO recruitView(long id) {
        return recruitRepository.findById(id).get();
    }

    // 팀 아이디와 매칭되는 모집글 조회
    public RecruitDTO recruitViewByTeamId(long teamId) {
        return recruitRepository.findByTeamId(teamId).get();
    }

    // 모집글 내용 수정
    public RecruitDTO recruitUpdate(long id, RecruitPostRequest request) {
        RecruitDTO existingRecruit = recruitRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recruit not found"));

        RecruitDTO updatedRecruit = RecruitDTO
                .builder()
                .id(id)
                .createdDate(request.getCreatedDate())
                .teamId(existingRecruit.getTeamId())
                .content(request.getContent())
                .status(1)
                .build();

        return recruitRepository.save(updatedRecruit);
    }

    // 모집글 삭제
    public void recruitDelete(long id) {
        recruitRepository.deleteById(id);
    }
}
