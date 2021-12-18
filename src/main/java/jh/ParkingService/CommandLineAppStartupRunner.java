package jh.ParkingService;

import jh.ParkingService.repository.park.JpaParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class CommandLineAppStartupRunner implements CommandLineRunner {

    EntityManager em;
    JpaParkRepository parkRepository = new JpaParkRepository(em);

    @Override
    public void run(String... args) throws Exception {


    }
}
