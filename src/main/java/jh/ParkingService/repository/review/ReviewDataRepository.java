package jh.ParkingService.repository.review;

import jh.ParkingService.domain.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewDataRepository {
    void add(Review review);

    void delete(int reviewUid);

    void update(int reviewUid, String reviewImageUrl, String reviewText, Short reviewRate, String reviewerNickName);

    List<Review> findByParkCode(String parkCode);

    Review findByReviewUid(int reviewUid);


}
