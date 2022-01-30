package jh.ParkingService.service.review;

import jh.ParkingService.dto.ReviewDto;
import jh.ParkingService.domain.Review;
import jh.ParkingService.repository.review.ReviewDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewDataServiceImpl {
    private final ReviewDataRepository reviewDataRepository;


    public void addReview_ExistImg(ReviewDto reviewDto){
        reviewDataRepository.add(reviewDto.toEntity_ExistImage());
    }

    public void addReview_NoneImg(ReviewDto reviewDto){
        reviewDataRepository.add(reviewDto.toEntity_NoneImage());
    }

    public void deleteReview(int reviewUid) { reviewDataRepository.delete(reviewUid); }

    public void updateReview(int reviewUid, String reviewImageUrl, String reviewText, Short reviewRate, String reviewerNickName){
        reviewDataRepository.update(reviewUid,reviewImageUrl,reviewText,reviewRate,reviewerNickName);
    }

    public void updateReview(int reviewUid, String reviewText, Short reviewRate, String reviewerNickName){
        reviewDataRepository.update(reviewUid, reviewText, reviewRate, reviewerNickName);
    }

    public List<Review> findAllReviewByParkCode(String parkCode) {
        return reviewDataRepository.findAllByParkCode(parkCode);
    }

    public List<Review
            > findTop5ReviewByParkCode(String parkCode) {
        return reviewDataRepository.findTop5ByParkCode(parkCode);
    }

    public Review findReviewByReviewUid(int reviewUid) { return reviewDataRepository.findByReviewUid(reviewUid);}
}
