package jh.ParkingService.config;

import jh.ParkingService.repository.likeReview.JpaLikeReviewRepository;
import jh.ParkingService.repository.likeReview.LikeReviewRepository;
import jh.ParkingService.repository.park.JpaParkRepository;
import jh.ParkingService.repository.park.ParkRepository;
import jh.ParkingService.repository.review.JpaReviewDataRepository;
import jh.ParkingService.repository.review.ReviewDataRepository;
import jh.ParkingService.repository.user.JpaUserRepository;
import jh.ParkingService.repository.user.UserRepository;
import jh.ParkingService.service.park.ParkServiceImpl;
import jh.ParkingService.service.review.ReviewDataServiceImpl;
import jh.ParkingService.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class MainConfig {
    private EntityManager em;
    
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

    @Bean
    public ReviewDataRepository reviewDataRepository() { return new JpaReviewDataRepository(em); }

    @Bean
    public ReviewDataServiceImpl reviewDataService() { return new ReviewDataServiceImpl(reviewDataRepository()); }

    @Bean
    public LikeReviewRepository likeReviewRepository() { return new JpaLikeReviewRepository(em); }
    }

