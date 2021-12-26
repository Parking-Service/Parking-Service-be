package jh.ParkingService.repository.review;

import jh.ParkingService.domain.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewDataRepository {
    void add(Review review);
    List<Review> findByParkCode(String parkCode);
}
