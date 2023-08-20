package com.multicampus.matchcode.controller.keitian.ljg;



import com.multicampus.matchcode.model.entity.ChargeHistoryDTO;
import com.multicampus.matchcode.model.entity.PointDTO;
import com.multicampus.matchcode.repository.ChargeHistoryRepository;
import com.multicampus.matchcode.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//포인트 조회
@Controller
public class PointController {


    private final PointRepository pointRepository;

    private final ChargeHistoryRepository chargeHistoryRepository;

    @Autowired
    public PointController(PointRepository pointRepository, ChargeHistoryRepository chargeHistoryRepository) {
        this.pointRepository = pointRepository;
        this.chargeHistoryRepository = chargeHistoryRepository;
    }


    //포인트표시
    @GetMapping("/point")
    public String viewPointPage(Model model, long userId) {
        PointDTO result = pointRepository.findByuserId(userId);
        model.addAttribute("Point", result.getPoint());
        //충전내역
        List<ChargeHistoryDTO> chargeHistories = chargeHistoryRepository.findAll();
        model.addAttribute("chargeHistories", chargeHistories);

        return "pointPage"; // pointPage는 Thymeleaf 템플릿의 이름입니다.
    }
}





//    @GetMapping("point/charge")
//    public String charge(Model model) {
//
//        return "point/chage";
//    }
//}

//    //충전 금액 지정
//    @GetMapping("point/charge")
//    public @ResponseBody void chargePoint(@RequestParam int amount, @RequestParam long id){
//        System.out.println(amount);
//        pointService.chargePoint(new PointChargeDTO(id, amount), id);
//    }
//}
    //public String charge(Model model) {

        //return "point/chage";


  // }
//}
    //@GetMapping("point/charge2")
   // public String charge2(Model model) {

        //return "point/chage2";
   // }
//}


//예시 코드
//        private final PointDAO pointDAO;
//
//        @Autowired
//        public PointController(PointDAO pointDAO) {
//          this.pointDAO = pointDAO;
//       }
//
//        @GetMapping("/point-values")
//        public String getPointValues(Model model) {
//            List<PointDTO> pointDTOList = pointDAO.findAll();
//
//            List<Integer> pointValues = pointDTOList.stream()
//                    .map(PointDTO::getPoint)
//                    .collect(Collectors.toList());
//
//            model.addAttribute("pointValues", pointValues);
//            return "point-values";
//        }
//        }