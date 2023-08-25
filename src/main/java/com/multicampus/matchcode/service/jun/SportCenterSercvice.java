package com.multicampus.matchcode.service.jun;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SportCenterSercvice {

    @Autowired
    private MapRepository mapRepository;

    public MapDTO save(MapDTO mapDTO){
        return mapRepository.save(mapDTO);
    }

}
