package jh.ParkingService.service.park;

import jh.ParkingService.csv.CSVParser;
import jh.ParkingService.domain.park.Park;
import jh.ParkingService.domain.park.ParkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Primary
@Transactional
@Component
public class ParkServiceImpl {
    private final EntityManager em;

    @Autowired
    private ParkRepository parkRepository;

    public void saveData(){
            //PARK_DATA 초기화
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
            em.createQuery("DELETE FROM Park").executeUpdate();
            em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

            CSVParser parser = new CSVParser(em);
            parser.read();

    }

    public List<Park> searchAddr(String addr, String lat, String lng){ return parkRepository.findByAddr(addr, lat, lng); }

    public List<Park> searchLotLoc(String lat, String lng){
        Double dLat = Double.parseDouble(lat);
        Double dLng = Double.parseDouble(lng);
        Double n = 0.02;

        return parkRepository.findByLocation(dLat, dLng, n);
    }

    public List<Park> searchTel(String telnum) { return parkRepository.findByPhoneNumberLike(telnum); }

    public List<Park> searchAll() { return parkRepository.findAll(); }
}
