package jh.ParkingService.service.parking;

import jh.ParkingService.domain.parking.Park;
import jh.ParkingService.repository.parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ParkingServiceImpl {

    private ParkingRepository parkingRepository;

    @Autowired
    public ParkingServiceImpl(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public void saveData(){
        parkingRepository.save();
    }
//    Optional<Park> searchAdrs(String address);
    public List<Park> searchLotLoc(String lat, String lng){
        return parkingRepository.findByLocation(lat, lng);
    }
//    Park findAll();
}
