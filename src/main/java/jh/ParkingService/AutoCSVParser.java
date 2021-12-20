package jh.ParkingService;

import jh.ParkingService.repository.park.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class AutoCSVParser {

    private ParkRepository parkingRepository;

    @Autowired
    public AutoCSVParser(ParkRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void parse(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Runtime runtime = Runtime.getRuntime();
                try {
                    Process p1 = runtime.exec("cmd /c start D:\\project\\ParkingService\\src\\main\\java\\jh\\ParkingService\\repository\\park\\autoCsvInstall.bat");
                    InputStream is = p1.getInputStream();
                    int i = 0;
                    while( (i = is.read() ) != -1) {
                        System.out.print((char)i);
                    }

                } catch(IOException ioException) {
                    System.out.println(ioException.getMessage() );
                }
            }
        }; timer.schedule(task, 1);
    }

}
