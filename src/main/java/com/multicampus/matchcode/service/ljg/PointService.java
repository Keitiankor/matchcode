package com.multicampus.matchcode.service.ljg;


import com.multicampus.matchcode.model.entity.MatchDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.model.request.ljg.ReserveRequest;
import com.multicampus.matchcode.repository.MatchRepository;
import com.multicampus.matchcode.repository.PointRepository;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class PointService {

    @Autowired
    PointRepository pointRepository;

    @Autowired
    MatchRepository matchRepository;

    public PointDTO pointCharge(PointDTO dto) {
//        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
////        PointDTO pointDTO = PointDTO
////                .builder()
////
////                .date(date)
////                .build();
        return pointRepository.save(dto);
    }

    public List<PointDTO> findAllByUserId(long userId) {
        return pointRepository.findAllByUserId(userId).get();


    }

    public int calculateTotalPoints(List<PointDTO> pointDTOs) {
        return pointDTOs.stream()
                .mapToInt(PointDTO::getPoint)
                .sum();
    }

    // 포인트 사용 내역 추가
    public long payPoints(ReserveRequest request) {
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        PointDTO pointDTO = PointDTO
                .builder()
                .date(date)
                .userId(request.getUserId())
                .point(-request.getPricePoints()) // Deducted points as a negative value
                .build();

        MatchDTO matchDTO = MatchDTO
                .builder()
                .mapId(request.getMapId())
                .matchDate(request.getMatchDate())
                .build();

        return matchRepository.save(matchDTO).getId();
    }
}

//취소요청보내기





//MatchDTO에서 mapId,matchDate, MapId에서 price가져오기
    //포인트환불
//    public PointDTO pointRefund(PointDTO dto) {
//        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
//        dto.setDate(date);
//        return pointRepository.save(dto);
//    }
