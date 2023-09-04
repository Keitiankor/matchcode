package com.multicampus.matchcode.service.ljg;


import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.model.request.ljg.ReserveRequest;
import com.multicampus.matchcode.repository.MapRepository;
import com.multicampus.matchcode.repository.MatchRepository;
import com.multicampus.matchcode.repository.PointRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    MatchRepository matchRepository;
    @Autowired
    MapRepository mapRepository;


    public PointDTO pointCharge(PointDTO dto) {
        return pointRepository.save(dto);
    }

    public List<PointDTO> findAllByMemberId(long memberId) {
        Optional<List<PointDTO>> odto = pointRepository.findAllByMemberId(memberId);

        if (odto.isPresent()) {
            return odto.get();
        }
        return null;
    }

    public int calculateTotalPoints(List<PointDTO> pointDTOs) {
        return pointDTOs.stream()
                        .mapToInt(PointDTO::getPoint)
                        .sum();
    }

    // 포인트 사용 내역 추가
    public long payPoints(ReserveRequest request) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        PointDTO pointDTO = PointDTO.builder()
                                    .date(date)
                                    .details("결제")
                                    .memberId(request.getMemberId())
                                    .point(-request.getPrice())
                                    .build();

        MatchDTO matchDTO = MatchDTO.builder()
                                    .mapId(request.getMapId())
                                    //.matchDate(request.getMatchDate())
                                    .build();

        matchRepository.save(matchDTO).getId();
        return pointRepository.save(pointDTO).getId();
    }

    public PointDTO refundPoints(PointDTO dto) {
        Timestamp paydate = Timestamp.valueOf(LocalDateTime.now());
        return pointRepository.save(dto);
    }
    public MapDTO findMapById(Long id) {
        return mapRepository.findById(id).orElse(null);
    }
}
