package jh.ParkingService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jh.ParkingService.domain.park.Park;
import jh.ParkingService.repository.park.ParkRepository;
import jh.ParkingService.service.park.ParkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController     //주용도는 Json 형태로 객체 데이터를 반환하는 것이며, Spring boot를 API 서버로 활용할 때 사용.
@RequestMapping("/lots")
public class LotsController {

    private ParkRepository parkRepository;
    private ParkServiceImpl parkService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public LotsController(ParkRepository parkRepository, ParkServiceImpl parkService) {
        this.parkRepository = parkRepository;
        this.parkService = parkService;
    }


    @RequestMapping("save")
    public void ParkDataSave() {
        parkService.saveData();
    }

//    @GetMapping("lots")
//    public @ResponseBody List<Park> AllParkDataSearch(){
//        return parkRepository.findAll();
//    }

    @GetMapping("address")
    public @ResponseBody List<Park> ParkDataAddrSearch(@RequestParam(value = "addr") String addr, @RequestParam(value = "latitude") String latitude, @RequestParam(value = "longitude") String longitude) {
    //객체를 json형태로 반환하기 위해 @ResponseBody 추가
        System.out.println("addr = " + addr);
        System.out.println("longitude = " + longitude);
        System.out.println("latitude = " + latitude);

        return parkService.searchAddr(addr, latitude, longitude);
    }

    @GetMapping("location")
    public @ResponseBody List<Park> ParkDataLocationSearch(@RequestParam(value = "latitude") String latitude, @RequestParam(value = "longitude") String longitude) {
        System.out.println("longitude = " + longitude);
        System.out.println("latitude = " + latitude);

        return parkService.searchLotLoc(latitude, longitude);
    }

    @GetMapping("tel")
    public @ResponseBody List<Park> ParkDataTelSearch(@RequestParam(value = "num") String num) {
        System.out.println("num = " + num);

        return parkService.searchTel(num);
    }


}
