package jh.ParkingService.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import io.swagger.annotations.ApiOperation;
import jh.ParkingService.aws.S3Uploader;
import jh.ParkingService.domain.Review;
import jh.ParkingService.repository.likeReview.LikeReviewRepository;
import jh.ParkingService.service.review.ReviewDataServiceImpl;
import jh.ParkingService.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
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

    //parkCode로 리뷰데이터 찾기
    @GetMapping("review")
    @ApiOperation(value = "주차장 리뷰 조회", notes = "주차장 코드로 리뷰 데이터를 조회한다.")
    public List<Review> findReviewByParkCode(@RequestParam("parkCode") String parkCode) {
        return reviewDataService.findReviewByParkCode(parkCode);
    }

    //reviewUid로 리뷰데이터 찾기
    @GetMapping("review/{reviewUid}")
    @ApiOperation(value = "주차장 리뷰 조회", notes = "리뷰UID로 리뷰 데이터를 조회한다.")
    public Review findReviewByReviewUid(@PathVariable int reviewUid) {
        return reviewDataService.findReviewByReviewUid(reviewUid);
    }

    //review 업로드
    @PostMapping("/review/upload")
    @ApiOperation(value = "주차장 리뷰 등록", notes = "주차장에 대한 리뷰를 등록한다.")
    public void uploadReview(@RequestParam("uid") String reviewerUid,
                             @RequestParam("parkCode") String parkCode,
                             @RequestParam(value = "imgs", required = false) List<MultipartFile> imgs,
                             @RequestParam("text") String reviewText,
                             @RequestParam("rate") Short reviewRate) throws IOException {


        reviewerUid = reviewerUid.replace("\"", "");
        parkCode = parkCode.replace("\"", "");
        reviewText = reviewText.replace("\"", "");

        String reviewDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        String reviewerNickName = userService.findUserNickName(reviewerUid);
        List<String> reviewImageUrls;
        short likeCount = 0;

        try {
            reviewImageUrls = s3Uploader.uploadFile(imgs, "review/" + parkCode + "/" + reviewerUid, reviewerUid); //aws s3에 업로드한 리뷰이미지 URL
            

            Review reviewData = new Review(reviewerUid, parkCode, reviewerNickName, reviewImageUrls.get(0),reviewImageUrls.get(1),reviewImageUrls.get(2),reviewImageUrls.get(3),reviewImageUrls.get(4), reviewText, reviewDate, likeCount, reviewRate);
            reviewDataService.addReview(reviewData);
        } catch (NullPointerException e) {

            Review reviewData = new Review(reviewerUid, parkCode, reviewerNickName, reviewText, reviewDate, likeCount, reviewRate);
            reviewDataService.addReview(reviewData);
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

//    //review 수정
//    @PutMapping("/review/update")
//    public void updateReview(@RequestParam("reviewUid") int reviewUid,
//                             @RequestParam("reviewerUid") String reviewerUid,
//                             @RequestParam(value = "img", required = false) MultipartFile img,
//                             @RequestParam(value = "del",required = false) List<String> deleteImgUrl,
//                             @RequestParam("text") String reviewText,
//                             @RequestParam("rate") Short reviewRate) throws IOException {
//
//        String reviewerNickName = userService.findUserNickName(reviewerUid);
//        Review reviewData = reviewDataService.findReviewByReviewUid(reviewUid);
//
//        s3Uploader.delete("review/" + reviewData.getParkCode() + "/" + reviewerUid + ".jpg");
//        try {    //reviewImage가 있을 때
//            String reviewImageUrl = s3Uploader.upload(img, "review/" + reviewData.getParkCode() + "/" + reviewerUid,reviewerUid);
//            reviewDataService.updateReview(reviewUid, reviewImageUrl, reviewText, reviewRate, reviewerNickName);
//        } catch (NullPointerException e) { //reviewImage가 없을 때
//            reviewDataService.updateReview(reviewUid, reviewText, reviewRate, reviewerNickName);
//        }
//
//    }

    @PutMapping("/review/like")
    @ApiOperation(value = "주차장 리뷰 좋아요 기능", notes = "리뷰에 좋아요를 남긴다.")
    public void likeReview(@RequestParam("reviewUid") int reviewUid,
                           @RequestParam("likeUserUid") String likeUserUid,
                           @RequestParam("like") boolean like) {

        likeReviewRepository.firstLikeCheck(reviewUid, likeUserUid);    //해당 리뷰에 처음으로 좋아요를 눌렀는지 확인, 처음이면 like_review 테이블에 데이터 생성
        likeReviewRepository.like(reviewUid, likeUserUid, like);  //like,unlike 판단해서 like_review, review 테이블에 반영
    }




}
