package jh.ParkingService.service.park;

import jh.ParkingService.domain.Park;
import jh.ParkingService.repository.park.ParkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class ParkServiceImpl {
    private final ParkRepository parkingRepository;


    public void saveData(){
        parkingRepository.save();
    }

    public List<Park> searchAddr(String addr, String lat, String lng){ return parkingRepository.findByAddr(addr, lat, lng); }

    public List<Park> searchLotLoc(String lat, String lng){ return parkingRepository.findByLocation(lat, lng); }

    public List<Park> searchTel(String telnum) { return parkingRepository.findByTel(telnum); }

    public List<Park> searchAll() { return parkingRepository.findAll(); }
}
