package com.multicampus.matchcode.service.mockup;

import com.multicampus.matchcode.model.entity.MapDTO;
import com.multicampus.matchcode.repository.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MakeMockup {

    @Autowired
    MapRepository mapRepository;

    public void makeMockupMap(int count) {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            mapRepository.save(MapDTO
                                       .builder()
                                       .phone(random
                                                      .ints(48, 58)
                                                      .limit(11)
                                                      .collect(StringBuilder::new,
                                                               StringBuilder::appendCodePoint,
                                                               StringBuilder::append
                                                      )
                                                      .toString())
                                       .sportsId(random.nextInt(1, 4))
                                       .latitude(random.nextDouble(126.9779692 - 0.5, 126.9779692 + 0.5))
                                       .longitude(random.nextDouble(37.566535 - 0.5, 37.566535 + 0.5))
                                       .managerName(random
                                                            .ints(97, 122)
                                                            .limit(12)
                                                            .collect(StringBuilder::new,
                                                                     StringBuilder::appendCodePoint,
                                                                     StringBuilder::append
                                                            )
                                                            .toString())
                                       .SportCenterName(random
                                                                .ints(97, 122)
                                                                .limit(12)
                                                                .collect(StringBuilder::new,
                                                                         StringBuilder::appendCodePoint,
                                                                         StringBuilder::append
                                                                )
                                                                .toString())
                                       .resistrationCode(random
                                                                 .ints(48, 58)
                                                                 .limit(12)
                                                                 .collect(StringBuilder::new,
                                                                          StringBuilder::appendCodePoint,
                                                                          StringBuilder::append
                                                                 )
                                                                 .toString())
                                       .price(random.nextInt(10000, 200001))
                                       .build());
        }
    }
}
