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

    public List<Review> findReviewByParkCode(String parkCode) {
        return reviewDataRepository.findByParkCode(parkCode);
    }
}
