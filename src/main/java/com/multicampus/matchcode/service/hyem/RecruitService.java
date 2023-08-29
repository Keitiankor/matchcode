package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.RecruitDTO;
import com.multicampus.matchcode.model.request.hyem.RecruitPostRequest;
import com.multicampus.matchcode.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RecruitService {

    @Autowired
    private RecruitRepository recruitRepository;

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
    public Page<RecruitDTO> recruitList(Pageable pageble) {
        return recruitRepository.findAll(pageble);
    }

    // 모집글 정보 불러오기
    public RecruitDTO recruitView(long id) {
        return recruitRepository.findById(id).get();
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
