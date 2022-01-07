package jh.ParkingService.dto;

import jh.ParkingService.entity.Review;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Data
public class ReviewDto {

    private int reviewUid;
    private String reviewerUid;
    private String parkCode;
    private String reviewerNickName;
    private String reviewImageUrl;
    private String reviewText;
    private String reviewDate;
    private Short likeCount;
    private Short reviewRate;

    public ReviewDto(String reviewerUid, String parkCode, String reviewerNickName, String reviewImageUrl, String reviewText, String reviewDate, Short likeCount, Short reviewRate) {
        this.reviewerUid = reviewerUid;
        this.parkCode = parkCode;
        this.reviewerNickName = reviewerNickName;
        this.reviewImageUrl = reviewImageUrl;
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
                .reviewImageUrl(reviewImageUrl)
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
