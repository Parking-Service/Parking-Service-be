package jh.ParkingService.controller;

import jh.ParkingService.domain.Park;
import jh.ParkingService.service.park.ParkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController     //주용도는 Json 형태로 객체 데이터를 반환하는 것이며, Spring boot를 API 서버로 활용할 때 사용.
@RequestMapping("/lots")
public class LotsController {

    private final ParkServiceImpl parkService;

    @Autowired
    public LotsController(ParkServiceImpl parkService) {
        this.parkService = parkService;
    }


    @RequestMapping("save")
    public void ParkDataSave() {
        parkService.saveData();
    }

    @GetMapping("all")
    public List<Park> AllParkDataSearch(){
        return parkService.searchAll();
    }

    @GetMapping("address")
    public List<Park> ParkDataAddrSearch(@RequestParam(value = "addr") String addr, @RequestParam(value = "latitude") String latitude, @RequestParam(value = "longitude") String longitude) {

        System.out.println("addr = " + addr);
        System.out.println("longitude = " + longitude);
        System.out.println("latitude = " + latitude);

        return parkService.searchAddr(addr, latitude, longitude);
    }

    @GetMapping("location")
    public List<Park> ParkDataLocationSearch(@RequestParam(value = "latitude") String latitude, @RequestParam(value = "longitude") String longitude) {
        System.out.println("longitude = " + longitude);
        System.out.println("latitude = " + latitude);

        return parkService.searchLotLoc(latitude, longitude);
    }

    @GetMapping("tel")
    public List<Park> ParkDataTelSearch(@RequestParam(value = "num") String num) {
        System.out.println("num = " + num);

        return parkService.searchTel(num);
    }
}
