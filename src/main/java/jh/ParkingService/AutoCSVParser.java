package jh.ParkingService;

import jh.ParkingService.service.park.ParkServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class AutoCSVParser {

    private final ParkServiceImpl parkService;

    @Scheduled(cron = "0 0 0 * * *")        //cron = 초 분 시 일 월 요일
    public void parse() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process p1 = runtime.exec("cmd /c start D:\\project\\ParkingService\\src\\main\\java\\jh\\ParkingService\\csv\\autoCsvInstall.bat");
            InputStream is = p1.getInputStream();
            int i = 0;
            while ((i = is.read()) != -1) {
                System.out.print((char) i);
            }
            parkService.saveData();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
