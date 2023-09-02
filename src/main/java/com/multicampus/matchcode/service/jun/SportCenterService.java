package com.multicampus.matchcode.service.jun;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.model.request.jun.SportCenterRequest;
import com.multicampus.matchcode.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .resistrationCode(request.getResistrationCode())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .build();
        System.out.println("SportCenter.insert : " + mapDTO);
        mapRepository.save(mapDTO);
    }

    public void update(SportCenterRequest request, MapDTO mapDTO) {
        MapDTO dto = MapDTO
                .builder()
                .id(mapDTO.getId())
                .sportsId(mapDTO.getSportsId())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .SportCenterName(request.getSportCenterName())
                .managerName(request.getManagerName())
                .phone(request.getPhone())
                .resistrationCode(request.getResistrationCode())
                .price(mapDTO.getPrice())
                .status(mapDTO.getStatus())
                .build();
        System.out.println("SportCenter : " + mapDTO);
        mapRepository.save(mapDTO);
    }

    //해당 id에 해당하는 스포츠 센터 정보를 가져오는 메서드
    public MapDTO findById(Long id) {
        return mapRepository
                .findById(id)
                .orElse(null);
    }

    public void delete(Long id) {
        System.out.println("SportCenter.delete" + id);
        mapRepository.deleteById(id);
    }

    public List<MapDTO> select(MapDTO mapDTO) {
        System.out.println("SportCenter.select : " + mapDTO);
        List<MapDTO> mapList = mapRepository.findAll();
        return mapList;
    }
}
