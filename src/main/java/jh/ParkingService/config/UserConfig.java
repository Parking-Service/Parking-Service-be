package jh.ParkingService.config;

import jh.ParkingService.repository.parking.JpaParkingRepository;
import jh.ParkingService.repository.parking.ParkingRepository;
import jh.ParkingService.repository.user.JpaUserRepository;
import jh.ParkingService.repository.user.UserRepository;
import jh.ParkingService.service.parking.ParkingServiceImpl;
import jh.ParkingService.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class UserConfig {
    private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public UserConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }


    @Bean
    public ParkingRepository parkingRepository() { return new JpaParkingRepository(em);}

    @Bean
    public ParkingServiceImpl parkingService() { return new ParkingServiceImpl(parkingRepository()); }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }

    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl(userRepository());
    }

}
