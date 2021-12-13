package jh.ParkingService.service.park;

import jh.ParkingService.domain.park.Park;
import jh.ParkingService.repository.park.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class ParkServiceImpl {

    private ParkRepository parkingRepository;

    @Autowired
    public ParkServiceImpl(ParkRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }




    public void saveData(){
        parkingRepository.save();
    }

    public List<Park> searchAddr(String addr, String lat, String lng){ return parkingRepository.findByAddr(addr, lat, lng); }

    public List<Park> searchLotLoc(String lat, String lng){ return parkingRepository.findByLocation(lat, lng); }

    public List<Park> searchTel(String telnum) { return parkingRepository.findByTel(telnum); }

    public List<Park> searchLots() { return parkingRepository.findAll(); }
}
