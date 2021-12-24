package jh.ParkingService.repository.review;

import jh.ParkingService.domain.Review;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewDataRepository {
    void add(Review review);
}
