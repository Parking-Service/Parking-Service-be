package jh.ParkingService.config;

import jh.ParkingService.repository.park.JpaParkRepository;
import jh.ParkingService.repository.park.ParkRepository;
import jh.ParkingService.repository.user.JpaUserRepository;
import jh.ParkingService.repository.user.UserRepository;
import jh.ParkingService.service.park.ParkServiceImpl;
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
    public ParkRepository parkRepository() { return new JpaParkRepository(em);}

    @Bean
    public ParkServiceImpl parkService() { return new ParkServiceImpl(parkRepository()); }

    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository(em);
    }

    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl(userRepository());
    }

}
