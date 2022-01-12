package jh.ParkingService.controller;

import io.swagger.annotations.ApiOperation;
import jh.ParkingService.aws.S3Uploader;
import jh.ParkingService.dto.ReviewDto;
import jh.ParkingService.domain.Review;
import jh.ParkingService.repository.likeReview.LikeReviewRepository;
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
    private final LikeReviewRepository likeReviewRepository;

    //parkCode로 모든 리뷰데이터 찾기
    @GetMapping("review/all")
    @ApiOperation(value = "주차장 전체 리뷰 조회", notes = "입력받은 주차장 코드로 등록된 모든 리뷰 데이터를 조회한다.")
    public List<Review> findAllReviewByParkCode(@RequestParam("parkCode") String parkCode) {
        return reviewDataService.findAllReviewByParkCode(parkCode);
    }

    //reviewUid로 리뷰데이터 찾기
    @GetMapping("review/{reviewUid}")
    @ApiOperation(value = "리뷰UID로 리뷰 조회", notes = "리뷰UID로 리뷰 데이터를 조회한다.")
    public Review findReviewByReviewUid(@PathVariable int reviewUid) {
        return reviewDataService.findReviewByReviewUid(reviewUid);
    }

    //parkCode로 Best 리뷰데이터 5개 찾기
    @GetMapping("review")
    @ApiOperation(value = "주차장 베스트 리뷰 조회", notes = "주차장 코드로 좋아요가 가장 많은 리뷰 데이터 5개를 조회한다.")
    public List<Review> findTop5ReviewByReviewUid(@RequestParam("parkCode") String parkCode) {
        return reviewDataService.findTop5ReviewByParkCode(parkCode);
    }





    //review 업로드
    @PostMapping("/review/upload")
    @ApiOperation(value = "주차장 리뷰 등록", notes = "주차장에 대한 리뷰를 등록한다.")
    public void uploadReview(@RequestParam("uid") String reviewerUid,
                             @RequestParam("parkCode") String parkCode,
                             @RequestParam(value = "img", required = false) MultipartFile img,
                             @RequestParam("text") String reviewText,
                             @RequestParam("rate") Float reviewRate) throws IOException {

        System.out.println("img = " + img);
        reviewerUid = reviewerUid.replace("\"", "");
        parkCode = parkCode.replace("\"", "");
        reviewText = reviewText.replace("\"", "");

        Long reviewDate = System.currentTimeMillis();
        String reviewerNickName = userService.findUserNickName(reviewerUid);
        String reviewImageUrl;
        short likeCount = 0;

        try {
            reviewImageUrl = s3Uploader.uploadFile(img, "parkingService/review/" + parkCode + "/" + reviewerUid, reviewerUid); //aws s3에 업로드한 리뷰이미지 URL
            ReviewDto reviewData = new ReviewDto(reviewerUid, parkCode, reviewerNickName, reviewImageUrl, reviewText, reviewDate, likeCount, reviewRate);

            reviewDataService.addReview_ExistImg(reviewData);
        } catch (NullPointerException e) {
            ReviewDto reviewData = new ReviewDto(reviewerUid, parkCode, reviewerNickName, reviewText, reviewDate, likeCount, reviewRate);

            reviewDataService.addReview_NoneImg(reviewData);
        }

    }

    //review 삭제
    @DeleteMapping("/review/remove")
    @ApiOperation(value = "주차장 리뷰 삭제", notes = "입력받은 reviewUid에 해당하는 주차장 리뷰를 삭제한다.")
    public void deleteReview(@RequestParam("reviewUid") int reviewUid) {
        reviewDataService.deleteReview(reviewUid);
        likeReviewRepository.removeLike(reviewUid);
    }

    //review 수정을 위한 ReviewData 전달
    @GetMapping("/review/update")
    @ApiOperation(value = "주차장 리뷰 수정", notes = "리뷰를 수정하기 위해 저장된 리뷰 내용을 리턴한다.")
    public Review updateReview(@RequestParam("reviewUid") int reviewUid) {
        return reviewDataService.findReviewByReviewUid(reviewUid);

    }

    //review 수정
    @PutMapping("/review/update")
    public void updateReview(@RequestParam("reviewUid") int reviewUid,
                             @RequestParam("reviewerUid") String reviewerUid,
                             @RequestParam(value = "img", required = false) MultipartFile img,
                             @RequestParam("text") String reviewText,
                             @RequestParam("rate") Short reviewRate) throws IOException {

        String reviewerNickName = userService.findUserNickName(reviewerUid);
        Review reviewData = reviewDataService.findReviewByReviewUid(reviewUid);


        try {    //reviewImage가 있을 때
            String reviewImageUrl = s3Uploader.uploadFile(img, "review/" + reviewData.getParkCode() + "/" + reviewerUid,reviewerUid);

            reviewDataService.updateReview(reviewUid, reviewImageUrl, reviewText, reviewRate, reviewerNickName);
        } catch (NullPointerException e) { //reviewImage가 없을 때
            reviewDataService.updateReview(reviewUid, reviewText, reviewRate, reviewerNickName);
        }

    }

    @PutMapping("/review/like")
    @ApiOperation(value = "주차장 리뷰 좋아요 기능", notes = "리뷰에 좋아요를 남긴다.")
    public void likeReview(@RequestParam("reviewUid") int reviewUid,
                           @RequestParam("likeUserUid") String likeUserUid,
                           @RequestParam("like") boolean like) {

        likeReviewRepository.firstLikeCheck(reviewUid, likeUserUid);    //해당 리뷰에 처음으로 좋아요를 눌렀는지 확인, 처음이면 like_review 테이블에 데이터 생성
        likeReviewRepository.like(reviewUid, likeUserUid, like);  //like,unlike 판단해서 like_review, review 테이블에 반영
    }




}
