package jh.ParkingService;

import jh.ParkingService.repository.park.JpaParkRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

@ServletComponentScan
@SpringBootApplication
class ParkingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingServiceApplication.class, args);

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
		};
		timer.scheduleAtFixedRate(task, 1000000, 86400000); // 1초 뒤 하루마다 반복실행
	}

}
