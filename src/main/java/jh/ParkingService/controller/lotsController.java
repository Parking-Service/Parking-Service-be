package jh.ParkingService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jh.ParkingService.repository.park.ParkRepository;
import jh.ParkingService.service.park.ParkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lots")
public class lotsController {

    private ParkRepository parkRepository;
    private ParkServiceImpl parkService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public lotsController(ParkRepository parkRepository, ParkServiceImpl parkService) {
        this.parkRepository = parkRepository;
        this.parkService = parkService;
    }


    @RequestMapping("save")
    public void ParkDataSave() {
        parkService.saveData();
    }

//    @GetMapping("lots/address")
//    public void ParkDataAddrSearch
//
//    @GetMapping("/lots/location")
//    public void ParkDataLocationSearch
//
//    @GetMapping("/lots/tel")

}
