package jh.ParkingService.service.review;

import jh.ParkingService.domain.review.ReviewRepository;
import jh.ParkingService.dto.ReviewDto;
import jh.ParkingService.domain.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Primary
@Transactional
@Service
public class ReviewDataServiceImpl {

    @Autowired
    private ReviewRepository reviewRepository;


    public void addReview_ExistImg(ReviewDto reviewDto){
        reviewRepository.save(reviewDto.toEntity_ExistImage());
    }

    public void addReview_NoneImg(ReviewDto reviewDto){
        reviewRepository.save(reviewDto.toEntity_NoneImage());
    }

    public void deleteReview(int reviewUid) { reviewRepository.delete(reviewUid); }

    public List<Review> findAllReviewByParkCode(String parkCode) {
        return reviewRepository.findByParkCode(parkCode);
    }

    public List<Review> findTop5ReviewByParkCode(String parkCode) {
        return reviewRepository.findTop5ByParkCodeOrderByLikeCount(parkCode);
    }

    public Optional<Review> findReviewByReviewUid(int reviewUid) { return reviewRepository.findByReviewUid(reviewUid);}
}
