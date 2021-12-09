package jh.ParkingService.service.parking;

import jh.ParkingService.domain.park.Park;
import jh.ParkingService.repository.park.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<Park> searchAdrs(String address, String lat, String lng){
        return parkingRepository.findByAdrs(address, lat, lng);
    }
    public List<Park> searchLotLoc(String lat, String lng){
        return parkingRepository.findByLocation(lat, lng);
    }

    public List<Park> findAll() { return parkingRepository.findAll(); }
}
