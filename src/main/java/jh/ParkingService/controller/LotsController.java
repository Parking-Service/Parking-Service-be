package jh.ParkingService.controller;

import io.swagger.annotations.ApiOperation;
import jh.ParkingService.entity.Park;
import jh.ParkingService.service.park.ParkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController     //주용도는 Json 형태로 객체 데이터를 반환하는 것이며, Spring boot를 API 서버로 활용할 때 사용.
@RequestMapping("/lots")
public class LotsController {

    private final ParkServiceImpl parkService;

    @Autowired
    public LotsController(ParkServiceImpl parkService) {
        this.parkService = parkService;
    }


    @PostMapping("save")
    @ApiOperation(value = "주차장 데이터 저장", notes = "주차장 데이터를 DB에 저장한다.")
    public void ParkDataSave() {
        parkService.saveData();
    }

    @GetMapping("all")
    @ApiOperation(value = "주차장 데이터 조회", notes = "모든 주차장 데이터를 조회한다.")
    public List<Park> AllParkDataSearch(){
        return parkService.searchAll();
    }

    @GetMapping("address")
    @ApiOperation(value = "주차장 데이터 조회", notes = "주소나 이름에 addr이 포함된 주차장을 현재 위도,경도 대비 가까운 순서대로 정렬하여 조회한다.")
    public List<Park> ParkDataAddrSearch(@RequestParam(value = "addr") String addr, @RequestParam(value = "latitude") String latitude, @RequestParam(value = "longitude") String longitude) {

        System.out.println("addr = " + addr);
        System.out.println("longitude = " + longitude);
        System.out.println("latitude = " + latitude);

        return parkService.searchAddr(addr, latitude, longitude);
    }

    @GetMapping("location")
    @ApiOperation(value = "주차장 데이터 조회", notes = "현재 위도/경도 +-n 에 있는 주차장을 조회한다.") //n은 JpaParkRepository 에서 조정.
    public List<Park> ParkDataLocationSearch(@RequestParam(value = "latitude") String latitude, @RequestParam(value = "longitude") String longitude) {
        System.out.println("longitude = " + longitude);
        System.out.println("latitude = " + latitude);

        return parkService.searchLotLoc(latitude, longitude);
    }

    @GetMapping("tel")
    @ApiOperation(value = "주차장 데이터 조회", notes = "전화번호로 주차장을 조회한다.")
    public List<Park> ParkDataTelSearch(@RequestParam(value = "num") String num) {
        System.out.println("num = " + num);

        return parkService.searchTel(num);
    }
}
