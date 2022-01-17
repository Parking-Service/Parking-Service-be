package jh.ParkingService.config;

import jh.ParkingService.domain.likereview.JpaLikeReviewRepository;
import jh.ParkingService.domain.likereview.LikeReviewRepository;
import jh.ParkingService.service.park.ParkServiceImpl;
import jh.ParkingService.service.review.ReviewDataServiceImpl;
import jh.ParkingService.service.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;

@EnableJpaRepositories(basePackages = "jh.ParkingService")
@Configuration
public class MainConfig {
    private EntityManager em;

    @Bean
    public ParkServiceImpl parkService() { return new ParkServiceImpl(em); }

    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl();
    }

    @Bean
    public ReviewDataServiceImpl reviewDataService() { return new ReviewDataServiceImpl(); }

    @Bean
    public LikeReviewRepository likeReviewRepository() { return new JpaLikeReviewRepository(em); }
    }

