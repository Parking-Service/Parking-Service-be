package jh.ParkingService.service.review;

import jh.ParkingService.domain.Review;
import jh.ParkingService.repository.review.ReviewDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Primary
@Transactional
@Service
public class ReviewDataServiceImpl {

    private final ReviewDataRepository reviewDataRepository;

    @Autowired
    public ReviewDataServiceImpl(ReviewDataRepository reviewDataRepository) {
        this.reviewDataRepository = reviewDataRepository;
    }

    public void addReview(Review review){
        reviewDataRepository.add(review);
    }

    public void deleteReview(int reviewUid) { reviewDataRepository.delete(reviewUid); }

//    public void updateReview(int reviewUid, String reviewImageUrl, String reviewText, Short reviewRate, String reviewerNickName){
//        reviewDataRepository.update(reviewUid,reviewImageUrl,reviewText,reviewRate,reviewerNickName);
//    }
//
//    public void updateReview(int reviewUid, String reviewText, Short reviewRate, String reviewerNickName){
//        reviewDataRepository.update(reviewUid, reviewText, reviewRate, reviewerNickName);
//    }

    public List<Review> findReviewByParkCode(String parkCode) {
        return reviewDataRepository.findByParkCode(parkCode);
    }

    public Review findReviewByReviewUid(int reviewUid) { return reviewDataRepository.findByReviewUid(reviewUid);}
}
