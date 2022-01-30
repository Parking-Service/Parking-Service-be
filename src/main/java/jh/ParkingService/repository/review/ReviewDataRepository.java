package jh.ParkingService.repository.review;

import jh.ParkingService.domain.Review;

import java.util.List;

public interface ReviewDataRepository {
    void add(Review review);

    void delete(int reviewUid);

    void update(int reviewUid, String reviewImageUrl, String reviewText, Short reviewRate, String reviewerNickName);

    void update(int reviewUid, String reviewText, Short reviewRate, String reviewerNickName);

    List<Review> findAllByParkCode(String parkCode);

    List<Review> findTop5ByParkCode(String parkCode);

    Review findByReviewUid(int reviewUid);


}
