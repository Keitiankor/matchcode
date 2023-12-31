package com.multicampus.matchcode.service.hyem;

import com.multicampus.matchcode.model.entity.ApplicationDTO;
import com.multicampus.matchcode.model.request.hyem.ApplicationRequest;
import com.multicampus.matchcode.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    // 가입 신청
    public void save(ApplicationRequest request, long memberId) {
        ApplicationDTO dto = ApplicationDTO
                .builder()
                .memberId(memberId)
                .teamId(request.getTeamId())
                .introduction(request.getIntroduction())
                .status(1) // 1: 가입 대기, 2: 가입 승인, 3: 가입 반려
                .build();
        applicationRepository.save(dto);
    }

    // 가입신청 리스트
    public Page<ApplicationDTO> applicationList(Pageable pageable) {
        return applicationRepository.findAll(pageable);
    }

    // 팀별 가입신청 리스트
    public Page<ApplicationDTO> teamApplicationList(long id, Pageable pageable) {
        return applicationRepository.findByTeamId(id, pageable);
    }

    // 가입신청 정보
    public ApplicationDTO applicationView(long id) {
        return applicationRepository
                .findById(id)
                .get();
    }

    // 가입신청 정보 수정
    public ApplicationDTO applicationUpdate(long id, ApplicationRequest request) {
        ApplicationDTO existingApplication = applicationRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
        ApplicationDTO applicationUpdate = ApplicationDTO
                .builder()
                .id(id)
                .memberId(request.getMemberId())
                .teamId(existingApplication.getTeamId())
                .introduction(request.getIntroduction())
                .status(1)
                .build();
        return applicationRepository.save(applicationUpdate);
    }

    // 가입신청 취소
    public void applicationCancel(long id) {
        applicationRepository.deleteById(id);
    }

    // 가입신청 여부
    public boolean memberApplicated(long memberId) {
        long isExist = applicationRepository.findIdByMemberIdExist(memberId);
        return isExist == 1;
    }

    // 가입 신청 정보 찾기
    public long findApplicatedId(long memberId) {
        return applicationRepository.findIdByMemberId(memberId);
    }
}
