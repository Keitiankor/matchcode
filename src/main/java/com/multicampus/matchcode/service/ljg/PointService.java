package com.multicampus.matchcode.service.ljg;

import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.model.entity.PointUseHistoryDTO;
import com.multicampus.matchcode.repository.PointRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    com.multicampus.matchcode.repository.PointUseHitoryRepository PointUseHitoryRepository;

    public PointDTO pointCharge(PointDTO dto) {
        System.out.println("2");
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        dto.setDate(date);
        return pointRepository.save(dto);
    }

    public List<PointDTO> findAllByUserId(long userId) {
        return pointRepository.findAllByUserId(userId).get();
    }

    public int calculateTotalPoints(List<PointDTO> pointDTOs) {
        return pointDTOs.stream().mapToInt(PointDTO::getPoint).sum();
    }

    // 포인트 사용 내역 추가
    public PointUseHistoryDTO usePoints(PointUseHistoryDTO dto) {
        Timestamp paydate = Timestamp.valueOf(LocalDateTime.now());
        dto.setPaydate(paydate);
        return PointUseHitoryRepository.save(dto);
    }

    // 포인트 사용내역
    public List<PointUseHistoryDTO> findAllUsePointByUserId(long userId) {
        return PointUseHitoryRepository.findAllByUserId(userId);
    }
}
