package jh.ParkingService;

import jh.ParkingService.repository.park.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;

@Component
public class AutoCSVParser {

    private ParkRepository parkingRepository;

    @Autowired
    public AutoCSVParser(ParkRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")        //cron = 초 분 시 일 월 요일
    public void parse() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process p1 = runtime.exec("cmd /c start D:\\project\\ParkingService\\src\\main\\java\\jh\\ParkingService\\repository\\park\\autoCsvInstall.bat");
            InputStream is = p1.getInputStream();
            int i = 0;
            while ((i = is.read()) != -1) {
                System.out.print((char) i);
            }
            parkingRepository.save();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
