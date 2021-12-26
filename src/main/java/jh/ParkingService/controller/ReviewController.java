package jh.ParkingService.controller;

import jh.ParkingService.aws.S3Uploader;
import jh.ParkingService.domain.Review;
import jh.ParkingService.service.review.ReviewDataServiceImpl;
import jh.ParkingService.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewController {
    private final S3Uploader s3Uploader;
    private final ReviewDataServiceImpl reviewDataService;
    private final UserServiceImpl userService;

    @GetMapping("review")
    public List<Review> findReviewByParkCode(@RequestParam("parkcode") String parkcode){

            return reviewDataService.findReviewByParkCode(parkcode);
    }


//    @GetMapping("review")
//    public Review findReviewByReviewUid(@RequestParam("reviewUid") int reviewuid){
//
//            return reviewDataService.findReviewByReviewUid(reviewuid);
//    }


    @PostMapping("/review/upload")
    public void uploadReview(@RequestParam("uid") String reviewerUid,
                       @RequestParam("parkCode") String parkCode,
                       @RequestParam("img") MultipartFile img,
                       @RequestParam("text") String reviewText,
                       @RequestParam("rate") Short reviewRate) throws IOException {


        String reviewImageUrl = s3Uploader.upload(img, "static"); //aws s3에 업로드한 리뷰이미지 URL
        String reviewDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        String reviewerNickName = userService.findUserNickName(reviewerUid);
        short likeCount = 0;

        Review reviewData = new Review(reviewerUid, parkCode, reviewerNickName, reviewImageUrl, reviewText, reviewDate, likeCount, reviewRate);

        reviewDataService.addReview(reviewData);

    }


    @DeleteMapping("/review/remove")
    public void deleteReview(@RequestParam("reviewUid") int reviewUid){
        reviewDataService.deleteReview(reviewUid);
    }

    @PutMapping("/review/update")
    public void updateReview(@RequestParam("reviewUid") int reviewUid,
                             @RequestParam("reviewerUid") String reviewerUid,
                             @RequestParam("img") MultipartFile img,
                             @RequestParam("text") String reviewText,
                             @RequestParam("rate") Short reviewRate) throws IOException {

        String reviewImageUrl = s3Uploader.upload(img, "static");
        String reviewerNickName = userService.findUserNickName(reviewerUid);

        reviewDataService.updateReview(reviewUid,reviewImageUrl,reviewText,reviewRate,reviewerNickName);
    }



}
