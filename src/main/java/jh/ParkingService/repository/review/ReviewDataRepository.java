package jh.ParkingService.repository.review;

import jh.ParkingService.entity.Review;

import java.util.List;

public interface ReviewDataRepository {
    void add(Review review);

    void delete(int reviewUid);

/*    void update(int reviewUid, String reviewImageUrl1, String reviewImageUrl2,String reviewImageUrl3,String reviewImageUrl4,String reviewImageUrl5, String reviewText, Short reviewRate, String reviewerNickName);

    void update(int reviewUid, String reviewText, Short reviewRate, String reviewerNickName);*/

    List<Review> findByParkCode(String parkCode);

    Review findByReviewUid(int reviewUid);


}
