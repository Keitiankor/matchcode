package com.multicampus.matchcode.service.jun;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.request.jun.SportCenterRequest;
import com.multicampus.matchcode.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportCenterSercvice {

    @Autowired
    private MapRepository mapRepository;

    public void save(SportCenterRequest request) {
        MapDTO mapDTO = MapDTO
                .builder()
                .managerName(request.getManagerName())
                .phone(request.getPhone())
                .SportCenterName(request.getSportCenterName())
                .areaId(request.getAreaId())
                .resistrationCode(request.getResistrationCode())
                .build();
        System.out.println("M.insert : " + mapDTO);
        mapRepository.save(mapDTO);
    }
}
