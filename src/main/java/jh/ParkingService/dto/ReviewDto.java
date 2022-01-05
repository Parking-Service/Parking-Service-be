package jh.ParkingService.dto;

import jh.ParkingService.entity.Review;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter
public class ReviewDto {

    private int reviewUid;
    private String reviewerUid;
    private String parkCode;
    private String reviewerNickName;
    private String reviewImageUrl1;
    private String reviewImageUrl2;
    private String reviewImageUrl3;
    private String reviewImageUrl4;
    private String reviewImageUrl5;
    private String reviewText;
    private String reviewDate;
    private Short likeCount;
    private Short reviewRate;

    public ReviewDto(String reviewerUid, String parkCode, String reviewerNickName, String reviewImageUrl1, String reviewImageUrl2, String reviewImageUrl3, String reviewImageUrl4, String reviewImageUrl5, String reviewText, String reviewDate, Short likeCount, Short reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewImageUrl1 = reviewImageUrl1;
        this.reviewImageUrl2 = reviewImageUrl2;
        this.reviewImageUrl3 = reviewImageUrl3;
        this.reviewImageUrl4 = reviewImageUrl4;
        this.reviewImageUrl5 = reviewImageUrl5;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.likeCount = likeCount;
        this.reviewRate = reviewRate;
    }

    public ReviewDto(String reviewerUid, String parkCode, String reviewerNickName, String reviewText, String reviewDate, Short likeCount, Short reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.likeCount = likeCount;
        this.reviewRate = reviewRate;
    }

    public Review toEntity_ExistImage(){
        return Review.builder()
                .reviewerUid(reviewerUid)
                .parkCode(parkCode)
                .reviewerNickName(reviewerNickName)
                .reviewImageUrl1(reviewImageUrl1)
                .reviewImageUrl2(reviewImageUrl2)
                .reviewImageUrl3(reviewImageUrl3)
                .reviewImageUrl4(reviewImageUrl4)
                .reviewImageUrl5(reviewImageUrl5)
                .reviewText(reviewText)
                .reviewDate(reviewDate)
                .likeCount(likeCount)
                .reviewRate(reviewRate)
                .build();
    }

    public Review toEntity_NoneImage(){
        return Review.NoneImageBuilder()
                .reviewerUid(reviewerUid)
                .parkCode(parkCode)
                .reviewerNickName(reviewerNickName)
                .reviewText(reviewText)
                .reviewDate(reviewDate)
                .likeCount(likeCount)
                .reviewRate(reviewRate)
                .build();
    }


}
