package com.multicampus.matchcode.service.jun;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.request.jun.SportCenterRequest;
import com.multicampus.matchcode.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportCenterService {

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

    public void update(SportCenterRequest request){
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

    //해당 id에 해당하는 스포츠 센터 정보를 가져오는 메서드
    public MapDTO findById(Long id) { return mapRepository.findById(id).orElse(null);}
}
