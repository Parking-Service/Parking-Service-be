package jh.ParkingService.controller;

import jh.ParkingService.aws.S3Uploader;
import jh.ParkingService.domain.Review;
import jh.ParkingService.service.review.ReviewDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {
    private final S3Uploader s3Uploader;
    private final ReviewDataServiceImpl reviewDataService;

    @PostMapping("upload")
    public void upload(@RequestParam("uid") String reviewerUid,
                       @RequestParam("parkcode") String parkCode,
                       @RequestParam("nick") String reviewrNickName,
                       @RequestParam("img") MultipartFile img,
                       @RequestParam("text") String reviewText,
                       @RequestParam("rate") Short reviewRate) throws IOException {


        String reviewImageUrl = s3Uploader.upload(img, "static"); //aws s3에 업로드한 리뷰이미지 URL
        String reviewDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        short likeCount = 0;

        Review reviewData = new Review(reviewerUid, parkCode, reviewrNickName, reviewImageUrl, reviewText, reviewDate, likeCount, reviewRate);

        reviewDataService.addReview(reviewData);

    }
}