package jh.ParkingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
class ParkingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingServiceApplication.class, args);
	}

}
